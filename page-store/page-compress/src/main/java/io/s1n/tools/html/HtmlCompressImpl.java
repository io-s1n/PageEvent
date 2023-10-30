package io.s1n.tools.html;

import in.wilsonl.minifyhtml.Configuration;
import in.wilsonl.minifyhtml.MinifyHtml;
import java.util.Objects;
import org.jboss.logging.Logger;

class HtmlCompressImpl implements HtmlCompress {

  private static final Logger LOGGER = Logger.getLogger(HtmlCompressImpl.class);

  static final Configuration DEFAULT_CONFIG = new Configuration.Builder()
      .setKeepHtmlAndHeadOpeningTags(false)
      .setKeepComments(false)
      .setMinifyJs(false)
      .setRemoveBangs(true)
      .setMinifyCss(false)
      .setDoNotMinifyDoctype(false)
      .setRemoveProcessingInstructions(false)
      .setKeepSpacesBetweenAttributes(false)
      .build();

  @Override
  public String compress(String source) {

    String compressed = MinifyHtml.minify(Objects.requireNonNull(source), DEFAULT_CONFIG);

    if (LOGGER.isDebugEnabled()) {
      int original = source.getBytes().length;
      int compress = compressed.getBytes().length;
      double compressPercent = 100 - ((double) original / compress * 100);
      LOGGER.debugv("compressed: {0}%", compressPercent);
    }
    return compressed;
  }
}
