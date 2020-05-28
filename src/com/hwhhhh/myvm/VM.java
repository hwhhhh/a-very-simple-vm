package com.hwhhhh.myvm;

import static com.hwhhhh.myvm.Opcode.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/18 17:48
 */
public class VM {
    int[] globals;
    int[] code;
    int[] stack;

    int ip;
    int sp = -1;
    int fp;

    static final int TRUE = 1;
    static final int FALSE = 0;

    boolean trace = false;

    public VM(Program program, int stackSize) {
        this(program.code, program.mainip, program.dataSize, stackSize);
    }

    public VM(int[] code, int main, int dataSize, int stackSize) {
        this.code = code;
        this.ip = main;
        this.globals = new int[dataSize];
        stack = new int[stackSize];
    }

    public void cpu() {
        while (ip < code.length) {

            if (trace) {
                System.err.printf("%-30s", disassemble());
            }

            int opcode = code[ip++];
            switch(opcode) {

                case IADD:  // 栈顶两元素相加
                    int b = stack[sp--];
                    int a = stack[sp--];
                    stack[++sp] = a + b;
                    break;

                case ISUB:  // 栈顶两元素相减
                    b = stack[sp--];
                    a = stack[sp--];
                    stack[++sp] = a - b;
                    break;

                case IMUL:  // 栈顶两元素相乘
                    b = stack[sp--];
                    a = stack[sp--];
                    stack[++sp] = a * b;
                    break;

                case IDIV:  // 相除
                    b = stack[sp--];
                    a = stack[sp--];
                    stack[++sp] = a / b;
                    break;

                case ILT:   // 如果a < b，b为栈顶元素，a为此栈顶
                    b = stack[sp--];
                    a = stack[sp--];
                    stack[++sp] = (a < b) ? TRUE : FALSE;
                    break;

                case IEQ:   // 如果a == b
                    b = stack[sp--];
                    a = stack[sp--];
                    stack[++sp] = (a == b) ? TRUE : FALSE;
                    break;

                case BR:    // 遇到分支，跳转
                    ip = code[ip++];
                    break;

                case BRT:   // 正确的分支
                    int addr = code[ip++];
                    if(stack[sp--] == TRUE) ip = addr;
                    break;

                case BRF:   // 错误的分支
                    addr = code[ip++];
                    if(stack[sp--] == FALSE) ip = addr;
                    break;

                case ICONST:    // 整型进栈
                    stack[++sp] = code[ip++];
                    break;

                case LOAD:  // 加载局部变量，根据偏移量获取值
                    int offset = code[ip++];
                    stack[++sp] = stack[fp + offset];
                    break;

                case GLOAD: // 加载全局变量
                    addr = code[ip++];
                    int v = globals[addr];
                    stack[++sp] = v;
                    break;

                case STORE: // 存储局部变量
                    offset = code[ip++];
                    stack[fp+offset] = stack[sp--];
                    break;

                case GSTORE: // 存储全局变量
                    v = stack[sp--];
                    addr = code[ip++];
                    globals[addr] = v;
                    break;

                case PRINT: // 打印并且出栈
                    System.out.println(stack[sp--]);
                    break;

                case POP:   // 栈顶出栈
                    sp--;
                    break;

                case HALT:  // 停机
                    if (trace) dumpDataMemory();
                    return;

                case CALL:  // 函数调用
                    addr = code[ip++];
                    int nargs = code[ip++];
                    stack[++sp] = nargs;
                    stack[++sp] = fp;
                    stack[++sp] = ip;
                    fp = sp;
                    ip = addr;
                    break;

                case RET:   // 返回
                    int rvalue = stack[sp--];
                    sp = fp;
                    ip = stack[sp--];
                    fp = stack[sp--];
                    nargs = stack[sp--];
                    sp -= nargs;
                    stack[++sp] = rvalue;
                    break;

                default:
                    throw new Error("Invalid opcode: " + opcode + ".");
            }
            if (trace) System.err.println(stackString());
        }
    }

    private String disassemble() {
        int opcode = code[ip];
        String opName = opcodes[opcode].name;
        StringBuilder buf = new StringBuilder();
        buf.append(String.format("%04d:\t%-11s", ip, opName));
        for (int i = 1; i <= opcodes[opcode].nOpnds; i++) {
            buf.append(code[ip + i] + " ");
        }
        return buf.toString();
    }

    private String stackString() {
        StringBuilder buf = new StringBuilder();
        buf.append("stack:[");
        for (int i = 0; i <= sp; i++) {
            buf.append(stack[i] + " ");
        }
        buf.append("]");
        return buf.toString();
    }

    private void dumpDataMemory() {
        System.err.println("\nGlobal data:");
        int addr = 0;
        for (int o : globals) {
            System.out.printf("%04d: %s\n", addr, o);
            addr++;
        }
        System.err.println();
    }
}

