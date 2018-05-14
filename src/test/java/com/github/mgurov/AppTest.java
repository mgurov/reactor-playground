package com.github.mgurov;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class AppTest {

    @Test
    public void checkReactorIsDoing() {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        Flux<Tuple2<String, String>> zipped = seq1.zipWith(seq2);


        Flux<String> composed = zipped.compose(new Function<Flux<Tuple2<String, String>>, Publisher<String>>() {
            @Override
            public Publisher<String> apply(Flux<Tuple2<String, String>> tuple2Flux) {
                return tuple2Flux.map(new Function<Tuple2<String, String>, String>() {
                    @Override
                    public String apply(Tuple2<String, String> objects) {
                        return objects.getT1() + objects.getT2();
                    }
                });
            }
        });

        composed.subscribe((t) -> {
            System.out.println("composed: " + t);
        });

        zipped.subscribe((t) -> {
            System.out.println("zipped: " + t);}
        );

    }
}
