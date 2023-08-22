package com.emv.qrcode.core.model.cpm;

import com.emv.qrcode.core.utils.HexDecoder;
import com.emv.qrcode.core.utils.HexEncoder;


import com.emv.qrcode.core.exception.DecodeValueException;
import org.apache.commons.lang.StringUtils;

public class BERTLNumeric extends BERTLV {

  private static final long serialVersionUID = -2791656176543560953L;

  public BERTLNumeric(final BERTag tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLNumeric(final byte[] tag, final byte[] value) {
    super(tag, value);
  }

  public BERTLNumeric(final byte[] tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public BERTLNumeric(final BERTag tag, final String value) {
    super(tag, StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  public final void setValue(final String value) {
    setValue(StringUtils.isNotEmpty(value) ? toPrimitives(value) : EMPTY_BYTES);
  }

  private static byte[] toPrimitives(final String value) {
    try {
      return HexDecoder.decodeHex(value.length() % 2 == 0 ? value : "0" + value);
    } catch (final Exception ex) {
      throw new DecodeValueException(value);
    }
  }

  @Override
  public String getStringValue() {
    return HexEncoder.encodeHex(value);
  }

}
