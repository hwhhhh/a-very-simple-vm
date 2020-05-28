package com.hwhhhh.myvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/26 21:10
 */
public class Homework {
    private static int a = 10;

    public static int exercise(int c) {
        int b = 20;
        return a * b - c;
    }

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        try {
            Thread.sleep(10000);
            for (int i = 0; i < 1000; i++) {
                System.err.println(exercise(i));
                result.add(i);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
