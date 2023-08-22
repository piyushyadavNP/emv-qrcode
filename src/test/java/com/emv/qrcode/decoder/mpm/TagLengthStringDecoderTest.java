package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.core.model.mpm.TagLengthString;

public class TagLengthStringDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException {
    final TagLengthString tagLengthString = DecoderMpm.decode("02041234", TagLengthString.class);

    assertThat(tagLengthString, not(nullValue()));

    assertThat(tagLengthString.getTag(), equalTo("02"));
    assertThat(tagLengthString.getLength(), equalTo(4));
    assertThat(tagLengthString.getValue(), equalTo("1234"));
  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException {
    final TagLengthString tagLengthString = DecoderMpm.decode("02041234", TagLengthString.class);

    assertThat(tagLengthString.toString(), equalTo("02041234"));
  }

}
