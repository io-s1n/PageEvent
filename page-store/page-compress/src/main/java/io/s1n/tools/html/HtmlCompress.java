package io.s1n.tools.html;

@FunctionalInterface
public interface HtmlCompress {

  String compress(String source);

  static HtmlCompress defaultCompressor() {
    return new HtmlCompressImpl();
  }
}
