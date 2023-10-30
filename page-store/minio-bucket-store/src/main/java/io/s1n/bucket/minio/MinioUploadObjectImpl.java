package io.s1n.bucket.minio;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import event.MetadataEvent;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;
import org.jboss.logging.Logger;

public class MinioUploadObjectImpl implements MinioUploadObject {

  private static final TypeReference<Map<String, String>> MAP_REF = new TypeReference<>() {
  };
  private static final Logger LOG = Logger.getLogger(MinioUploadObjectImpl.class);
  private static final ObjectMapper MAPPER = new ObjectMapper();
  public static final int PART_SIZE = 5 * 1024 * 1024;
  private final MinioClient client;
  private final String bucket;

  public MinioUploadObjectImpl(MinioDefaultClient minioDefaultClient) {
    this.client = minioDefaultClient.getDefaultClient();
    this.bucket = minioDefaultClient.defaultBucket();
  }

  @Override
  public String write(UUID id, byte[] data, MetadataEvent event) {
    try (InputStream dataStream = new ByteArrayInputStream(data)) {
      PutObjectArgs uploadObject = toUploadObject(id, dataStream, event);
      return client.putObject(uploadObject).versionId();
    } catch (Exception e) {
      LOG.warn(e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public String write(UUID id, String data, MetadataEvent event) {
    return write(id, data.getBytes(UTF_8), event);
  }

  private PutObjectArgs toUploadObject(UUID name, InputStream dataStream, MetadataEvent event)
      throws IOException {
    return PutObjectArgs.builder()
        .bucket(bucket)
        .object(name.toString())
        .contentType("text/html")
        .userMetadata(MAPPER.convertValue(event, MAP_REF))
        .stream(dataStream, dataStream.available(), PART_SIZE)
        .build();
  }
}
