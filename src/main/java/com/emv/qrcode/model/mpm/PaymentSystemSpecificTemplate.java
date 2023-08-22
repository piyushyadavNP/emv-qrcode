package com.emv.qrcode.model.mpm;

import java.util.Objects;



import com.emv.qrcode.core.model.TLV;

import lombok.Setter;
import org.apache.commons.lang.StringUtils;

@Setter
public class PaymentSystemSpecificTemplate implements TLV<String, PaymentSystemSpecific> {

  private static final long serialVersionUID = -1445641777082739037L;

  private String tag;

  private PaymentSystemSpecific value;

  @Override
  public String getTag() {
    return tag;
  }

  @Override
  public PaymentSystemSpecific getValue() {
    return value;
  }

  @Override
  public String toString() {

    if (Objects.isNull(value)) {
      return StringUtils.EMPTY;
    }

    final String string = value.toString();

    if (StringUtils.isBlank(string)) {
      return StringUtils.EMPTY;
    }

    return String.format("%s%02d%s", tag, string.length(), string);
  }

}
