package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import com.emv.qrcode.core.utils.HexEncoder;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public class CommonDataTransparentTemplateTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    final BERTLAlphanumeric value = new BERTLAlphanumeric(new byte[] { 0x00 }, "1234");
    commonDataTransparentTemplate.addAdditionalData(value);

    assertThat(commonDataTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
    assertThat(HexEncoder.encodeHex(commonDataTransparentTemplate.getBytes()), equalTo("6406000431323334"));
  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    final BERTLAlphanumeric value = new BERTLAlphanumeric(new byte[] { 0x0 }, StringUtils.EMPTY);

    commonDataTransparentTemplate.addAdditionalData(value);

    assertThat(HexEncoder.encodeHex(commonDataTransparentTemplate.getBytes()), equalTo(StringUtils.EMPTY));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final CommonDataTransparentTemplate commonDataTransparentTemplate = new CommonDataTransparentTemplate();

    assertThat(HexEncoder.encodeHex(commonDataTransparentTemplate.getBytes()), equalTo(StringUtils.EMPTY));

  }

}
