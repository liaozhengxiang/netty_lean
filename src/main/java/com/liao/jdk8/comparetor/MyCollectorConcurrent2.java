package com.liao.jdk8.comparetor;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义并发收集器
 *
 * @ThreadNoSafe
 * @param <T>
 */
public class MyCollectorConcurrent2<T> implements Collector<T, Set<T>, Map<T, T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return (set, item) -> {
            //这里将存在线程安全问题，因为中间的set容器是线程不安全的
            System.out.println("set:" + set + " " + Thread.currentThread().getName());
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        return (set1, set2) -> {
            System.out.println("combiner " + set1 + ":" + set2 + " " + Thread.currentThread().getName());
            set1.addAll(set2);
            return set1;
        };
    }

    /**
     * 用于将中间容器转换成结果容器的方法
     * 此示例中间容器和结果容器均为Set<T>
     *
     * @return
     */
    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        //characteristics 含有Characteristics.IDENTITY_FINISH 时候将不会调用
        return (set) -> {
            Map<T, T> resultMap = new HashMap<>();

            set.forEach(item -> {
                resultMap.put(item, item);
            });

            return resultMap;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        //combiner()方法将会被并发调用
        //Characteristics.CONCURRENT 返回这个特性标识，代表中间容器可以并发使用，即多个线程将共享一个中间容器，而不是每个线程使用单独的容器
        return new HashSet<>(Arrays.asList(Characteristics.CONCURRENT, Characteristics.UNORDERED));
    }
}
