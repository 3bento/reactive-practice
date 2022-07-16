package dev.ebento.hellomutiny;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import java.time.Duration;

public class DelayIt {

    private static final Logger LOG = Logger.getLogger(DelayIt.class);

    public static void main(String[] args) {
        Uni.createFrom().item(1)
                .onItem().transform(i -> "hello-"+i)
                .onItem().delayIt().by(Duration.ofMillis(100))
                .subscribe().with(LOG::info);
    }
}
