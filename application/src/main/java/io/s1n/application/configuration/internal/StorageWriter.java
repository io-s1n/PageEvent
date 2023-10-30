package io.s1n.application.configuration.internal;


import io.quarkus.arc.DefaultBean;
import io.s1n.bucket.minio.MinioDefaultClient;
import io.s1n.bucket.minio.MinioUploadObject;
import io.s1n.bucket.minio.MinioUploadObjectImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
class StorageWriter {

  @Inject
  MinioConfig minioConfig;

  @Produces
  @DefaultBean
  public MinioUploadObject uploadObject() {
    return new MinioUploadObjectImpl(new MinioDefaultClient(minioConfig));
  }
}
