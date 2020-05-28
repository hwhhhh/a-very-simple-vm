package com.hwhhhh.myvm;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/18 17:07
 */
public class Opcode {
    static final int IADD   = 1;      // 整数加法
    static final int ISUB   = 2;      // 整数减法
    static final int IMUL   = 3;      // 整数乘法
    static final int IDIV   = 4;      // 整数除法
    static final int ILT    = 5;      // 整数小于
    static final int IEQ    = 6;      // 整数相等
    static final int BR     = 7;      // 分支跳转，后接跳转地址
    static final int BRT    = 8;      // 如果正确的分支，接正确的跳转地址
    static final int BRF    = 9;      // 如果错误的分支，接错误的跳转地址
    static final int ICONST = 10;      // 整型进栈，接整数
    static final int LOAD   = 11;     // 加载局部变量，接局部变量关于fp的偏移量
    static final int GLOAD  = 12;     // 加载全局变量，接全局变量的存储地址
    static final int STORE  = 13;     // 存储局部变量，接存储地址
    static final int GSTORE = 14;     // 存储全局变量，接全局变量的存储地址
    static final int PRINT  = 15;     // 打印栈顶元素并出栈
    static final int POP    = 16;     // 栈顶元素出栈
    static final int HALT   = 17;     // 停止运行
    static final int CALL   = 18;     // 函数，接俩参数，参数1为函数地址，参数2为参数个数
    static final int RET    = 19;     // 返回函数

    public static class Instruction {
        String name;
        int nOpnds = 0;
        Instruction(String name) { this(name, 0); }
        Instruction(String name, int nOpnds) {
            this.name = name;
            this.nOpnds = nOpnds;
        }
    }

    static Instruction[] opcodes = {
            null,
            new Instruction("IADD"),
            new Instruction("ISUB"),
            new Instruction("IMUL"),
            new Instruction("IDIV"),
            new Instruction("ILT"),
            new Instruction("IEQ"),
            new Instruction("BR", 1),
            new Instruction("BRT", 1),
            new Instruction("BRF", 1),
            new Instruction("ICONST", 1),
            new Instruction("LOAD", 1),
            new Instruction("GLOAD", 1),
            new Instruction("STORE", 1),
            new Instruction("GSTORE", 1),
            new Instruction("PRINT"),
            new Instruction("POP"),
            new Instruction("HALT"),
            new Instruction("CALL", 2),
            new Instruction("RET")
    };

}
