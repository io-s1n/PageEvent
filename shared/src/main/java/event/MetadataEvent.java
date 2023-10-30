package event;

import java.util.UUID;

public final class MetadataEvent {

  private final String transactionId;
  private final String emitter;
  private final String webUrl;
  private final String bucketUrl;
  private final String category;
  private final Long timestamp;

  private MetadataEvent(Builder builder) {
    this.transactionId = builder.id == null ? UUID.randomUUID().toString() : builder.id;
    this.webUrl = builder.webUrl;
    this.bucketUrl = builder.bucketUrl;
    this.category = builder.category;
    this.timestamp = builder.timestamp;
    this.emitter = builder.emitter;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public String getEmitter() {
    return emitter;
  }

  public String getWebUrl() {
    return webUrl;
  }

  public String getBucketUrl() {
    return bucketUrl;
  }

  public String getCategory() {
    return category;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private String id;
    private String emitter;
    private String webUrl;
    private String bucketUrl;
    private String category;
    private Long timestamp;

    private Builder() {
    }

    public Builder id(UUID val) {
      this.id = val.toString();
      return this;
    }

    public Builder emitter(String val) {
      this.emitter = val;
      return this;
    }

    public Builder webUrl(String val) {
      this.webUrl = val;
      return this;
    }

    public Builder bucketUrl(String val) {
      this.bucketUrl = val;
      return this;
    }

    public Builder category(String val) {
      this.category = val;
      return this;
    }

    public Builder timestamp(Long val) {
      this.timestamp = val;
      return this;
    }

    public MetadataEvent build() {
      return new MetadataEvent(this);
    }
  }
}
