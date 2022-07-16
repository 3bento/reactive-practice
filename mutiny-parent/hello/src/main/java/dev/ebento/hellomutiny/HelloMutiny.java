package dev.ebento.hellomutiny;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;


public class HelloMutiny {

    private static final Logger LOG = Logger.getLogger(HelloMutiny.class);
    public static void main(String[] args) {
        Uni.createFrom().item("hello")
                .onItem().transform(item -> item + " mutiny")
                .onItem().transform(String::toUpperCase)
                .subscribe().with(item -> LOG.info(">> " + item));
    }
}
