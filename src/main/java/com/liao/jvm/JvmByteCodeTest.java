package com.liao.jvm;

public class JvmByteCodeTest {

    String str = "Welcome";

    public static Integer i = 10;

    public static void main(String[] args) {

        i = 11;
    }

    public static void setI(Integer i) {
        JvmByteCodeTest.i = i;
    }

    private void setStr(String str) {

        synchronized (str) {
            System.out.println(str);
        }
    }
}
