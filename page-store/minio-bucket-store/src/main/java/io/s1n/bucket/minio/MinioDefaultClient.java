package io.s1n.bucket.minio;

import internal.MinioCredential;
import io.minio.MinioClient;

public class MinioDefaultClient {

  private final MinioCredential credential;

  public MinioDefaultClient(MinioCredential credential) {
    this.credential = credential;
  }

  public MinioClient getDefaultClient() {
    return MinioClient.builder()
        .endpoint(credential.endpoint())
        .credentials(credential.access(), credential.secret())
        .build();
  }

  public String defaultBucket() {
    return credential.bucket();
  }
}
