package com.liao.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamGroupByTest {
    public static void main(String[] args) {

        Person person = new Person("student1", 10, 30);
        Person person1 = new Person("student1", 11, 43);
        Person person2 = new Person("student2", 12, 83);
        Person person3 = new Person("student3", 13, 13);
        Person person4 = new Person("student1", 13, 13);

        List<Person> personList = Arrays.asList(person, person1, person2, person3, person4);


//        personList.stream().collect(Collectors.groupingBy(Person::getScore)).forEach((key, value) -> {
//            System.out.println(key + ":" + value);
//        });

        //相当于 select count(*) from xxx group by score
        personList.stream().collect(Collectors.groupingBy(Person::getScore, Collectors.counting()))
                .forEach((key, value) -> {
                    System.out.println(key + ":" + value);
                });


        System.out.println("-------------");
        //相当于select avg(score) from xxx group by score;
        personList.stream().collect(Collectors.groupingBy(Person::getScore, Collectors.averagingLong(Person::getScore)))
                .forEach((key, value) -> {
                    System.out.println(key + ":" + value);
                });


        System.out.println("-------------");
        //相当于select avg(score) from xxx group by getName， score;
        personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.groupingBy(Person::getScore)))
                .forEach((key, value) -> {
                    System.out.println(key + ":" + value);
                });

        System.out.println("spliterator-- tryAdvance-----------");
        personList.stream().spliterator().tryAdvance(System.out::println);


        System.out.println("spliterator forEach-----------");
        personList.stream().spliterator().forEachRemaining(System.out::println);

    }


}

class Person {

    private String name;

    private int age;

    private long score;


    public Person(String name, int age, long score) {

        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}