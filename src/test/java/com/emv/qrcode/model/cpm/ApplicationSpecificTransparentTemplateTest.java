package com.emv.qrcode.model.cpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import com.emv.qrcode.core.utils.HexEncoder;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.cpm.BERTLAlphanumeric;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

public class ApplicationSpecificTransparentTemplateTest {

  @Test
  public void testSuccessToHex() throws IOException {

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();

    final BERTLAlphanumeric value = new BERTLAlphanumeric(new byte[] { 0x00 }, "1234");

    applicationSpecificTransparentTemplate.addAdditionalData(value);

    assertThat(applicationSpecificTransparentTemplate.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_APPLICATION_SPECIFIC_TRANSPARENT_TEMPLATE));
    assertThat(HexEncoder.encodeHex(applicationSpecificTransparentTemplate.getBytes()), equalTo("6306000431323334"));

  }

  @Test
  public void testSuccessToHexWhenValueIsEmpty() throws IOException {

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();

    final BERTLAlphanumeric value = new BERTLAlphanumeric(new byte[] { 0x00 }, StringUtils.EMPTY);

    applicationSpecificTransparentTemplate.addAdditionalData(value);

    assertThat(HexEncoder.encodeHex(applicationSpecificTransparentTemplate.getBytes()), equalTo(StringUtils.EMPTY));

  }

  @Test
  public void testSuccessToHexWhenValueIsNull() throws IOException {

    final ApplicationSpecificTransparentTemplate applicationSpecificTransparentTemplate = new ApplicationSpecificTransparentTemplate();

    applicationSpecificTransparentTemplate.addAdditionalData(null);

    assertThat(HexEncoder.encodeHex(applicationSpecificTransparentTemplate.getBytes()), equalTo(StringUtils.EMPTY));

  }

}
