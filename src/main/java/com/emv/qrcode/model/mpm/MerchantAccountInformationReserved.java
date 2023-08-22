package com.emv.qrcode.model.mpm;



import lombok.Getter;
import org.apache.commons.lang.StringUtils;

@Getter
public class MerchantAccountInformationReserved implements MerchantAccountInformation {

  private static final long serialVersionUID = -5918006580406564067L;

  private String value;

  public MerchantAccountInformationReserved() {
    super();
  }

  public MerchantAccountInformationReserved(final String value) {
    this.value = value;
  }

  @Override
  public String toString() {

    if (StringUtils.isBlank(value)) {
      return StringUtils.EMPTY;
    }

    return value;
  }

}
