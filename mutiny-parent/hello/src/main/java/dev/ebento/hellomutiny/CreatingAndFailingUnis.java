package dev.ebento.hellomutiny;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class CreatingAndFailingUnis {

    private static final Logger LOG = Logger.getLogger(CreatingAndFailingUnis.class);

    private static final String EXCEPTION_MESSAGE = "Failed with {0}";

    public static void main(String[] args) {

        AtomicInteger counter = new AtomicInteger();

        // create
        Uni<Integer> normalItem = Uni.createFrom().item(1);
        Uni<Integer> counterItem = Uni.createFrom().item(counter::getAndIncrement);

        // fail
        Uni<Object> calledException = Uni.createFrom().failure(new Exception("!!!calledException!!!"))
                .onFailure()
                .transform(e -> new IllegalCallerException(e.getMessage(), e))
                .onFailure()
                .invoke(e -> LOG.warnv(e, EXCEPTION_MESSAGE, e.getMessage()));

        Uni<Object> calledException2NoLogThrow = Uni.createFrom().failure(new Exception("!!!calledException2NoLogThrow!!!"))
                .onFailure()
                .transform(e -> new IllegalCallerException(e.getMessage(), e))
                .onFailure()
                .invoke(e -> LOG.warnv( EXCEPTION_MESSAGE, e.getMessage()));

        Uni<Object> stateException = Uni.createFrom().failure(() -> new Exception("!!!stateException!!!"))
                .onFailure()
                .transform(e->new IllegalStateException(e.getMessage(), e))
                .onFailure()
                .invoke(e -> LOG.errorv(e, EXCEPTION_MESSAGE, e.getMessage()));

        Uni<Object> noSuchMethodException = Uni.createFrom().failure(() -> new Exception("!!!noSuchMethodException!!!"))
                .onFailure()
                .transform(e->new NoSuchMethodException(e.getMessage()))
                .onFailure()
                .invoke(e -> LOG.errorv(e,EXCEPTION_MESSAGE, e.getMessage()));

        normalItem.subscribe().with(LOG::info);
        counterItem.subscribe().with(LOG::info);

        calledException.subscribe().with(LOG::info);
        calledException2NoLogThrow.subscribe().with(LOG::info);
        stateException.subscribe().with(LOG::info);
        noSuchMethodException.subscribe().with(LOG::info);
    }

}
