package internal;

public interface MinioCredential {

  String endpoint();

  String bucket();

  String access();

  String secret();
}
