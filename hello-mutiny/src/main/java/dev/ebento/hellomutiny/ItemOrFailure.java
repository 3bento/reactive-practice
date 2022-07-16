package dev.ebento.hellomutiny;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.Cancellable;

import java.time.LocalDateTime;

public class ItemOrFailure {

    public static void main(String[] args) {

        Uni<String> uni = Uni.createFrom().item("It should work")
                .onItem().transform(i -> i + " on ")
                .onItem().transform(String::toLowerCase)
                .onItem().transform(i -> i + "not even value ##SECOND##")
                .onItem().transform(i -> {
                    int second = LocalDateTime.now().getSecond();
                    i = i.replace("##SECOND##", "Second:"+ second);
                    if(second % 2 == 0) {
                        i = i.replace("should", "should not");
                        throw new IllegalStateException(i);
                    }
                    return i;
                });


        uni.subscribe().with(
                System.out::println,
                failure -> System.out.println("Failed with "+ failure)
        );
    }

}
