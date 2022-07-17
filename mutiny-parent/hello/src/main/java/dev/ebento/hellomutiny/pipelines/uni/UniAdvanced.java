package dev.ebento.hellomutiny.pipelines.uni;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

public class UniAdvanced {

    private static final Logger LOG = Logger.getLogger(UniAdvanced.class);

    public static void main(String[] args) {
        Uni<String> uni = Uni.createFrom().emitter(em -> {

            String result = "A";

            em.complete(result);
        });
        Uni<String> uni2 = Uni.createFrom()
                .completionStage(uni.onItem().transform(e -> e + " B")
                        .subscribe().asCompletionStage());

        Uni<String> uni3 = Uni.createFrom()
                .completionStage(uni2.onItem().transform(e -> e + " C")
                        .subscribe().asCompletionStage());

        Uni<String> uni4 = Uni.createFrom()
                .completionStage(uni3.onItem().transform(e -> e + " D")
                        .subscribe().asCompletionStage());

        uni4.subscribe().with(LOG::info);
    }
}
