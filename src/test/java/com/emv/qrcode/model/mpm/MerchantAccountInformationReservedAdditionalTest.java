package com.emv.qrcode.model.mpm;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.emv.qrcode.core.model.mpm.TagLengthString;

public class MerchantAccountInformationReservedAdditionalTest {

  @Test
  public void testSuccessToString() {

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional();
    merchantAccountInformation.setGloballyUniqueIdentifier("hoge");
    merchantAccountInformation.addPaymentNetworkSpecific(tagLengthString);

    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifier() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional("hoge");
    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierAndPaymentNetworkSpecific() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional("hoge", "01", "abcd");
    assertThat(merchantAccountInformation.toString(), equalTo("0004hoge0104abcd"));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierIsNull() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional(null);
    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

  @Test
  public void testSuccessToStringConstructorGloballyUniqueIdentifierAndPaymentNetworkSpecificIsNull() {
    final MerchantAccountInformationReservedAdditional merchantAccountInformation = new MerchantAccountInformationReservedAdditional(null, null, null);
    assertThat(merchantAccountInformation.toString(), equalTo(StringUtils.EMPTY));
  }

}
