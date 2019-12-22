package com.liao.jdk8.comparetor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyCollectorTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "world", "233", "2sd", "ad", "hello");

        Set<String> resultSet = list.stream().collect(new MyCollector<>());

        for (String setItem : resultSet) {
            System.out.println(setItem);
        }

        System.out.println("----------");

        list.stream().collect(new MyCollector2<>()).forEach((key, val) -> {
            System.out.println(key + ":" + val);
        });


        System.out.println("----------");
        //并发收集器
//        list.parallelStream().collect(new MyCollectorConcurrent2<>()).forEach((k, v) -> {
//            System.out.println(k + ":" + v);
//        });

        System.out.println("---------");
        list.parallelStream().collect(new MyCollectorConcurrent<>()).forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

    }
}
