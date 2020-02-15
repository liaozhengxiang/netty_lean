package com.liao.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * Stream的短路操作
 */
public class StreamShortCurit {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "world", "hello world");

        /**
         * 这里只会输出 “hello”，因为这里的findFirst是个短路操作，只要找到一个符合，其他都不需要执行。
         */
        list.stream().mapToInt(str -> {
            System.out.println(str);
            return str.length();
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);


    }

}
