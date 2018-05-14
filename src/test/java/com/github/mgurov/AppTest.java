package com.github.mgurov;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.Arrays;
import java.util.List;

public class AppTest {

    @Test
    public void checkReactorIsDoing() {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        Tuple2<String, String> result = seq1.zipWith(seq2).blockLast();

        System.out.println(result);
    }
}
