package rest;

import java.util.UUID;

public record MetadataRestResource(
    UUID transactionId,
    String emitter,
    String webUrl,
    String category,
    String data
) {

}
