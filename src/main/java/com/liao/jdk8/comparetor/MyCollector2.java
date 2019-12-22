package com.liao.jdk8.comparetor;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollector2<T> implements Collector<T, Set<T>, Map<T, T>> {
    @Override
    public Supplier<Set<T>> supplier() {
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        return Set::add;
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        return (set1, set2) -> {
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
        return new HashSet<>(Collections.singletonList(Characteristics.UNORDERED));
    }
}
