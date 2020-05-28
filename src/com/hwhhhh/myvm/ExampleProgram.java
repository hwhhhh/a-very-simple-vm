package com.hwhhhh.myvm;

import static com.hwhhhh.myvm.Opcode.*;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/19 17:24
 */
public class ExampleProgram {
    /**
     * 循环计数
     * int result = 0;
     * for(int i = 0; i < 10; i ++) {
     *     result++;
     * }
     */
    static Program countToTen = new Program(0, 0, new int[]{
            ICONST, 0,          // 0
            ICONST, 0,          // 2
            LOAD, 1,            // 4
            ICONST, 10,         // 6
            ILT,                // 8
            BRF, 27,            // 9
            // result ++;
            LOAD, 0,            // 11
            ICONST, 1,          // 13
            IADD,               // 15
            STORE, 0,           // 16
            // i ++;
            LOAD, 1,            // 18
            ICONST, 1,          // 20
            IADD,               // 22
            STORE, 1,           // 23
            BR, 4,              // 25
            // 局部变量i出栈
            POP,                // 27
            PRINT,              // 28
            HALT                // 29
    });

    // 测试函数返回
    static Program simpleFunctionCall = new Program(0, 2, new int[]{
            // 主函数
            CALL, 5, 0,         // 0
            PRINT,              // 3
            HALT,               // 4
            // 返回 30
            ICONST, 30,         // 5
            RET                 // 6
    });

    /**
     * 测试递归返回
     * int result;
     * main {
     *     div(405, 45);
     *     println(result + "");
     * }
     * div(int x, int y) {
     *     if (x > y) {
     *         result++;
     *         div(x - y, y);
     *     }
     * }
     */
    static Program divide = new Program(28, 1, new int[]{
            // 函数div(x, y) x 为-4， y 为 -3
            LOAD, -4,       // 0
            LOAD, -3,       // 2
            ILT,            // 4
            BRF, 10,        // 5
            GLOAD, 0,       // 7
            RET,            // 9
            // result ++；
            GLOAD, 0,       // 10
            ICONST, 1,      // 12
            IADD,           // 14
            GSTORE, 0,      // 15
            // div(x-y, y)
            LOAD, -4,       // 17
            LOAD, -3,       // 19
            ISUB,           // 21
            LOAD, -3,       // 22
            CALL, 0, 2,     // 24
            RET,            // 27
            // 主函数
            ICONST, 135,    // 28 被除数
            ICONST, 45,     // 30 除数
            ICONST, 0,      // 32 全局变量result
            GSTORE, 0,      // 34
            CALL, 0, 2,     // 36
            PRINT,          // 39
            HALT            // 40
    });

    /**
     * 阶乘
     * factorial(int x) {
     *     if (x < 2) {
     *         return 1;
     *     } else {
     *         return x*factorial(x - 1);
     *     }
     * }
     */
    static Program factorial = new Program(22, 0, new int[]{
            LOAD, -3,                      // 0
            ICONST, 2,                     // 2
            ILT,                           // 4
            BRF, 10,                       // 5
            ICONST, 1,                     // 7
            RET,                           // 9
            // Return x * factorial(x-1)
            LOAD, -3,                      // 10
            LOAD, -3,                      // 12
            ICONST, 1,                     // 14
            ISUB,                          // 16
            CALL, 0, 1,                    // 17
            IMUL,                          // 20
            RET,                           // 21
            // 主函数
            ICONST, 5,                     // 22
            CALL, 0, 1,                    // 24
            PRINT,                         // 27
            HALT                           // 28
    });
}
