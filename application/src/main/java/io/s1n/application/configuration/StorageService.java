package io.s1n.application.configuration;

import event.MetadataEvent;
import io.s1n.bucket.minio.MinioUploadObject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Instant;
import java.util.UUID;
import rest.MetadataRestResource;

@ApplicationScoped
public class StorageService {

  @Inject
  MinioUploadObject storageCachedClient;

  public String processResource(MetadataRestResource resource) {
    UUID transactionId = resource.transactionId();
    MetadataEvent metadata = asEvent(transactionId, resource);

    return storageCachedClient.write(transactionId, resource.data(), metadata);
  }

  private static MetadataEvent asEvent(UUID transactionId, MetadataRestResource resource) {
    return MetadataEvent.builder()
        .id(transactionId)
        .bucketUrl("")
        .webUrl(resource.webUrl())
        .category(resource.category())
        .emitter(resource.webUrl())
        .timestamp(Instant.now().toEpochMilli())
        .build();
  }
}
