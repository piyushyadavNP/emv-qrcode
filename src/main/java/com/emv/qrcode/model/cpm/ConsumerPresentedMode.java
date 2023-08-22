package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.emv.qrcode.core.utils.HexEncoder;

import com.emv.qrcode.core.model.cpm.BERTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ConsumerPresentedMode implements Serializable {

  private static final long serialVersionUID = -1395429978639674565L;

  // Payload Format Indicator
  @Setter
  private PayloadFormatIndicator payloadFormatIndicator;

  // Application Template
  private final List<ApplicationTemplate> applicationTemplates = new LinkedList<>();

  // Common Data Template
  @Setter
  private CommonDataTemplate commonDataTemplate;

  // Other template
  private final List<BERTemplate<byte[]>> otherTemplates = new LinkedList<>();

  public void addApplicationTemplate(final ApplicationTemplate applicationTemplate) {
    applicationTemplates.add(applicationTemplate);
  }

  public void addOtherTemplate(final BERTemplate<byte[]> otherTemplate) {
    otherTemplates.add(otherTemplate);
  }

  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      if (Objects.nonNull(payloadFormatIndicator)) {
        out.write(payloadFormatIndicator.getBytes());
      }

      for (final BERTemplate<byte[]> applicationTemplate : applicationTemplates) {
        out.write(applicationTemplate.getBytes());
      }

      if (Objects.nonNull(commonDataTemplate)) {
        out.write(commonDataTemplate.getBytes());
      }

      for (final BERTemplate<byte[]> otherTemplate : otherTemplates) {
        out.write(otherTemplate.getBytes());
      }

      return out.toByteArray();
    }
  }

  public String toBase64() throws IOException {
    return Base64.getEncoder().encodeToString(getBytes());
  }

  public String toHex() throws IOException {
    return HexEncoder.encodeHex(getBytes());
  }

}
