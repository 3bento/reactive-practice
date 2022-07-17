package dev.ebento.hellomutiny.pipelines.multi;

import io.smallrye.mutiny.Multi;
import org.jboss.logging.Logger;

public class TheMultiType {
    private static final Logger LOG = Logger.getLogger(TheMultiType.class);

    public static void main(String[] args) {
        Multi.createFrom().items(1,2,3,4,5)
                .onItem().transform(i -> i*3*3*3*3*3*3*3)
                .select().first(3)
                .onFailure().recoverWithItem(0)
                .subscribe().with(LOG::info);
    }
}
