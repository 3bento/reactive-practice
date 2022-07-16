package dev.ebento.hellomutiny;

import io.smallrye.mutiny.Uni;

import java.time.Duration;

public class DelayIt {

    public static void main(String[] args) {
        Uni.createFrom().item(1)
                .onItem().transform(i -> "hello-"+i)
                .onItem().delayIt().by(Duration.ofMillis(100))
                .subscribe().with(System.out::println);
    }
}
