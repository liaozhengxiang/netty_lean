package com.liao.jdk8;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class StreamFlatMap {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello world", "hell grade", "good hello world");


        list.stream()
                .flatMap(item-> Arrays.stream(item.split(" ")))
                .distinct()
                .forEach(System.out::println);


        ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);

//        DateTimeFormatter.ofPattern("").parse();
    }
}
