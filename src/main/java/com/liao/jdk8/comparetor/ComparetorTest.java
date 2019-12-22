package com.liao.jdk8.comparetor;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparetorTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "test", "world", "nihao");

        list.stream().sorted((item1,item2)->item2.charAt(0)-item1.charAt(0))
                .peek(System.out::println)
                .count()
                ;


        Collections.sort(list,Comparator.comparingInt(item->item.charAt(0)));



        Collections.sort(list,Comparator.comparingInt((String item)->item.charAt(0)).reversed());



    }

}
