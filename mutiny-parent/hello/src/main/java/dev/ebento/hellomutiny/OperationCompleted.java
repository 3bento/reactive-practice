package dev.ebento.hellomutiny;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

public class OperationCompleted {

    private static final Logger LOG = Logger.getLogger(OperationCompleted.class);

    public static void main(String[] args){
        Uni<Void> withVoid = Uni.createFrom().item(1)
                .invoke(LOG::info)
                .replaceWithVoid();

        Uni<Void> nullItem = Uni.createFrom().nullItem();

        withVoid.subscribeAsCompletionStage()
                .whenComplete(LOG::info);

        nullItem.subscribe().with(LOG::info);

    }
}
