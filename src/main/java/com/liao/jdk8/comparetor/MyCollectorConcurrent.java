package com.liao.jdk8.comparetor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义并发收集器
 *
 * @param <T>
 * @ThreadSafe
 */
public class MyCollectorConcurrent<T> implements Collector<T, Map<T, T>, Map<T, T>> {
    @Override
    public Supplier<Map<T, T>> supplier() {
        return ConcurrentHashMap::new;
    }

    @Override
    public BiConsumer<Map<T, T>, T> accumulator() {
        return (map, item) -> {
            System.out.println("map:" + map + " " + Thread.currentThread().getName());
            map.put(item, item);
        };
    }

    @Override
    public BinaryOperator<Map<T, T>> combiner() {
        return (map1, map2) -> {
            map1.putAll(map2);
            return map1;
        };
    }

    /**
     * 用于将中间容器转换成结果容器的方法
     * 此示例中间容器和结果容器均为Set<T>
     *
     * @return
     */
    @Override
    public Function<Map<T, T>, Map<T, T>> finisher() {
        //characteristics 含有Characteristics.IDENTITY_FINISH 时候将不会调用
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Characteristics> characteristics() {
        //combiner()方法将会被并发调用
        //Characteristics.CONCURRENT 返回这个特性标识，代表中间容器可以并发使用，即多个线程将共享一个中间容器，而不是每个线程使用单独的容器
        return new HashSet<>(Arrays.asList(Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED));
    }
}
