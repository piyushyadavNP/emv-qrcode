package com.emv.qrcode.core.model.cpm;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.exception.DecodeValueException;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class BERTLCompressedNumericTest {

  @Test
  public void testSuccess() throws IOException {
    final BERTLCompressedNumeric bertlv1 = new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, "0123456789");
    assertThat(bertlv1.toHex(), equalTo("5A050123456789"));
    assertThat(bertlv1.getStringValue(), equalTo("0123456789"));

    final BERTLCompressedNumeric bertlv2 = new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, "01234567899");
    assertThat(bertlv2.toHex(), equalTo("5A0601234567899F"));
    assertThat(bertlv2.getStringValue(), equalTo("01234567899F"));

    bertlv2.setValue("01");
    assertThat(bertlv2.toHex(), equalTo("5A0101"));
    assertThat(bertlv2.getStringValue(), equalTo("01"));

    bertlv2.setValue("");
    assertThat(bertlv2.toHex(), equalTo(StringUtils.EMPTY));
    assertThat(bertlv2.getStringValue(), equalTo(StringUtils.EMPTY));

    final BERTLCompressedNumeric bertlv3 = new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89 });
    assertThat(bertlv3.toHex(), equalTo("5A050123456789"));
    assertThat(bertlv3.getStringValue(), equalTo("0123456789"));

    final BERTLCompressedNumeric bertlv4 = new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN.getBytes(), "0123456789");
    assertThat(bertlv4.toHex(), equalTo("5A050123456789"));
    assertThat(bertlv4.getStringValue(), equalTo("0123456789"));

    final BERTLCompressedNumeric bertlv5 = new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN.getBytes(), "");
    assertThat(bertlv5.toHex(), equalTo(StringUtils.EMPTY));
    assertThat(bertlv5.getStringValue(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testFail() throws IOException {
    final DecodeValueException decodeValueException = catchThrowableOfType(() -> new BERTLCompressedNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, "AG000000666666"), DecodeValueException.class);
    assertThat(decodeValueException.getMessage(), equalTo("Characters outside of the expected range Hex '[0-9a-fA-F]'. Invalid value 'AG000000666666'"));
    assertThat(decodeValueException.getValue(), equalTo("AG000000666666"));
  }

}
