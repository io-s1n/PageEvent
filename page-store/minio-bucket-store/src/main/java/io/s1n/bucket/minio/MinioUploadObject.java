package io.s1n.bucket.minio;

import event.MetadataEvent;
import java.util.UUID;

public interface MinioUploadObject {

  String write(UUID id, byte[] data, MetadataEvent metadataEvent);

  String write(UUID id, String data, MetadataEvent metadataEvent);
}
