package com.hwhhhh.myvm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.hwhhhh.myvm.Opcode.opcodes;

/**
 * @Description detail
 * Created by Hwhhhh on 2020/5/21 20:40
 */
public class Loader {
    private static void write(String pathName, List<int[]> codeList) {
        try {
            DataOutputStream out =  new DataOutputStream(new FileOutputStream(pathName));
            boolean isOpcode;
            for (int[] i : codeList) {
                isOpcode = true;
                for (int j : i) {
                    if (isOpcode) {
                        out.write(j);
                        isOpcode = false;
                    } else {
                        out.writeInt(j);
                    }
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String assembly(String pathName) {
        File file = new File(pathName);
        BufferedReader reader = null;
        String fileName = null;
        List<int[]> codeList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempLine;
            String[] code;

            while ((tempLine = reader.readLine()) != null) {    // 一次读取一行
                code = tempLine.split(" ");
                switch (code[0].toUpperCase()) {
                    case "IADD":  // 栈顶两元素相加
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{1});
                        break;

                    case "ISUB":  // 栈顶两元素相减
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{2});
                        break;

                    case "IMUL":  // 栈顶两元素相乘
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{3});
                        break;

                    case "IDIV":  // 相除
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{4});
                        break;
                    case "ILT":   // 如果a < b，b为栈顶元素，a为此栈顶
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{5});
                        break;

                    case "IEQ":   // 如果a == b
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{6});
                        break;

                    case "BR":    // 遇到分支，跳转
                        if (code.length != 2) {
                            throw new Error("不合法的参数输入");
                        }
                        int[] ins = new int[2];
                        ins[0]=7;
                        if (code[1].matches("\\d+")) {
                            ins[1] = Integer.valueOf(code[1]);
                            codeList.add(ins);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;

                    case "BRT":   // 正确的分支
                        if (code.length != 2) {
                            throw new Error("不合法的参数输入");
                        }
                        ins = new int[2];
                        ins[0] = 8;
                        if (code[1].matches("\\d+")) {
                            ins[1] = Integer.valueOf(code[1]);
                            codeList.add(ins);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;
                    case "BRF":   // 错误的分支
                        if (code.length != 2) {
                            throw new Error("不合法的参数输入");
                        }
                        ins = new int[2];
                        ins[0] = 9;
                        if (code[1].matches("\\d+")) {
                            ins[1] = Integer.valueOf(code[1]);
                            codeList.add(ins);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;

                    case "ICONST":    // 整型进栈
                        if (code.length == 1) {
                            throw new Error("不合法的参数输入");
                        } else {
                            int[] iconstInt;
                            for (int i = 1, j = 0; i < code.length; i++) {
                                iconstInt = new int[2];
                                iconstInt[j++] = 10;
                                if (code[i].matches("\\d+")) {
                                    iconstInt[j] = Integer.valueOf(code[i]);
                                    j = 0;
                                    codeList.add(iconstInt);
                                } else {
                                    throw new Error("不合法的参数输入");
                                }
                            }
                        }
                        break;

                    case "LOAD":  // 加载局部变量，根据偏移量获取值
                        if (code.length != 2) {
                            throw new Error("不合法的参数输入");
                        }
                        ins = new int[2];
                        ins[0] = 11;
                        if (code[1].matches("\\d+")) {
                            ins[1] = Integer.valueOf(code[1]);
                            codeList.add(ins);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;

                    case "GLOAD": // 加载全局变量
                        if (code.length != 2) {
                            throw new Error("不合法的参数输入");
                        }
                        ins = new int[2];
                        ins[0] = 12;
                        if (code[1].matches("\\d+")) {
                            ins[1] = Integer.valueOf(code[1]);
                            codeList.add(ins);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;

                    case "STORE": // 存储局部变量
                        if (code.length != 2) {
                            throw new Error("不合法的参数输入");
                        }
                        ins = new int[2];
                        ins[0] = 13;
                        if (code[1].matches("\\d+")) {
                            ins[1] = Integer.valueOf(code[1]);
                            codeList.add(ins);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;

                    case "GSTORE": // 存储全局变量
                        if (code.length != 2) {
                            throw new Error("不合法的参数输入");
                        }
                        ins = new int[2];
                        ins[0] = 14;
                        if (code[1].matches("\\d+")) {
                            ins[1] = Integer.valueOf(code[1]);
                            codeList.add(ins);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;

                    case "PRINT": // 打印并且出栈
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{15});
                        break;

                    case "POP":   // 栈顶出栈
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{16});
                        break;

                    case "HALT":  // 停机
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{17});
                        break;

                    case "CALL":  // 函数调用
                        if (code.length != 3) {
                            throw new Error("不合法的参数输入");
                        }
                        int[] callInt = new int[3];
                        callInt[0] = 18;
                        if (code[1].matches("\\d+") && code[2].matches("\\d+")) {
                            callInt[1] = Integer.valueOf(code[1]);
                            callInt[2] = Integer.valueOf(code[2]);
                            codeList.add(callInt);
                        } else {
                            throw new Error("不合法的参数输入");
                        }
                        break;

                    case "RET":   // 返回
                        if (code.length != 1) {
                            throw new Error("不合法的参数输入");
                        }
                        codeList.add(new int[]{19});
                        break;

                    default:
                        throw new Error("Invalid opcode. ");
                }
            }
            reader.close();
            fileName = file.getName().split("\\.")[0] + ".dat";
            write(fileName, codeList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }

    public static int[] loader(String fileName) {
        DataInputStream in = null;
        int[] result = null;
        try {
            in = new DataInputStream(new FileInputStream(fileName));
            int byteLength = in.available();
            List<Integer> codeList = new ArrayList<>();
            int temp;
            for (int i = 0; i < byteLength; i++) {
                temp = in.readByte();
                codeList.add(temp);
                if (temp > 6 && temp < 15) {
                    codeList.add(in.readInt());
                    i = i + 4;
                } else if (temp == 18) {
                    codeList.add(in.readInt());
                    codeList.add(in.readInt());
                    i = i + 8;
                }
            }
            result = new int[codeList.size()];
            temp = 0;
            for (int i : codeList) {
                result[temp] = i;
                temp++;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
