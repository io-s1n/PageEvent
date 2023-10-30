package io.s1n.tools.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HtmlCompressTest {

  private static final int COMPRESS_THRESHOLD = 25;
  static String SAMPLE_HTML;

  static HtmlCompress htmlCompress;

  @BeforeAll
  static void setup() throws IOException {
    htmlCompress = HtmlCompress.defaultCompressor();

    try (InputStream testHtmlResource = HtmlCompressTest.class.getClassLoader()
        .getResourceAsStream("sample.html")) {
        SAMPLE_HTML = new BufferedReader(new InputStreamReader(testHtmlResource))
            .lines().collect(Collectors.joining("\n"));
      }
  }

  @Test
  void tryCompress() {
    String result = htmlCompress.compress(SAMPLE_HTML);
    int sourceLength = SAMPLE_HTML.getBytes().length;
    int resultLength = result.getBytes().length;
    double compressPercent = 100 - ((double) resultLength / sourceLength * 100);

    Assertions.assertTrue(compressPercent > COMPRESS_THRESHOLD);
  }
}