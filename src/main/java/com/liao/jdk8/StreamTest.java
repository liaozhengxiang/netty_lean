package com.liao.jdk8;

import java.util.*;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {

        Stream<String> test = Stream.of("test", "test2", "test3");

//        List<String> collect = test.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        Map<String, String> resultMap = test.collect(HashMap::new, (map, data) -> map.put(data, data), HashMap::putAll);

        resultMap.forEach((key, value) -> {
            System.out.println("key:" + key + " value:" + value);
        });


//        String[] strings = test.toArray(String[]::new);
//
//        String[] strArr=new String[3];
//
//        Arrays.stream(strings).forEach(System.out::println);


        List<String> list = Arrays.asList("hello", "world", "test", "nihao");

        list.stream().map(item -> item).filter(item->true).forEach(System.out::println);


    }


}
