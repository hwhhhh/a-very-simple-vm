package com.hwhhhh.myvm;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/19 17:19
 */
public class Program {
    int[] code; // 代码
    int mainip; // 主函数入口
    int dataSize; // 需要的数据区大小

    public Program(int mainip, int dataSize, int[] code)  {
        this.code = code;
        this.mainip = mainip;
        this.dataSize = dataSize;
    }
}
