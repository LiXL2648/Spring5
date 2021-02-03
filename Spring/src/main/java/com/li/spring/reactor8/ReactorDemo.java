package com.li.spring.reactor8;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReactorDemo {

    private static Integer[] array;

    public static void main(String[] args) {

        // just 方法直接声明
        Flux.just(1, 2, 3, 4).subscribe(System.out::println);
        Mono.just(5).subscribe(System.out::println);

        // 使用数组
        array = new Integer[]{1, 2, 3, 4};
        Flux.fromArray(array);

        // 使用集合
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);

        // 使用 Stream 流
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }
}
