package io.s1n.application.configuration.internal;

import internal.MinioCredential;
import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "minio")
public interface MinioConfig extends MinioCredential {

  @Override
  String endpoint();

  @Override
  String bucket();

  @Override
  String access();

  @Override
  String secret();
}
