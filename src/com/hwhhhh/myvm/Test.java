package com.hwhhhh.myvm;

import static com.hwhhhh.myvm.ExampleProgram.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/19 17:31
 */
public class Test {
    public static void main(String[] args) {
        int stackSize = 100;
        Program program = factorial;
//        String fileName = Loader.assembly("countToTen.txt");
//        int[] code = Loader.loader(fileName);
//        Program program = new Program(0, 2, code);
        VM vm = new VM(program, stackSize); // 将程序装载进虚拟机
        vm.trace = true;
        vm.cpu();

    }
}
