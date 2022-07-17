package dev.ebento.hellomutiny.pipelines.uni;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;

public class ItemOrFailure {

    private static final Logger LOG = Logger.getLogger(ItemOrFailure.class);

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
               LOG::info,
                failure -> LOG.errorv("Failed with {0}", failure.getMessage(), failure)
        );
    }

}
