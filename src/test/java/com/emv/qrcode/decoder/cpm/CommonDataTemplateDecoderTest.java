package com.emv.qrcode.decoder.cpm;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import com.emv.qrcode.core.utils.HexDecoder;
import org.junit.Test;

import com.emv.qrcode.core.exception.DuplicateTagException;
import com.emv.qrcode.core.exception.PresentedModeException;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

public class CommonDataTemplateDecoderTest {

  @Test
  public void testSuccessDecode() throws PresentedModeException, IOException {

    final byte[] source1 = HexDecoder.decodeHex("621A4F07A0000000555555570F1234567890123458D191220112345F");

    final CommonDataTemplate commonDataTemplate1 = DecoderCpm.decode(source1, CommonDataTemplate.class);

    assertThat(commonDataTemplate1, not(nullValue()));

    assertThat(commonDataTemplate1.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(commonDataTemplate1.getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(commonDataTemplate1.getTrack2EquivalentData().getTag(), equalTo(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA));

    final byte[] source2 = HexDecoder.decodeHex("621C641A4F07A0000000555555570F1234567890123458D191220112345F");

    final CommonDataTemplate commonDataTemplate2 = DecoderCpm.decode(source2, CommonDataTemplate.class);

    assertThat(commonDataTemplate2, not(nullValue()));

    assertThat(commonDataTemplate2.getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TEMPLATE));
    assertThat(commonDataTemplate2.getCommonDataTransparentTemplate().getTag(), equalTo(ConsumerPresentedModeFieldCodes.ID_COMMON_DATA_TRANSPARENT_TEMPLATE));
    assertThat(commonDataTemplate2.getCommonDataTransparentTemplate().getApplicationDefinitionFileName().getTag(), equalTo(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME));
    assertThat(commonDataTemplate2.getCommonDataTransparentTemplate().getTrack2EquivalentData().getTag(), equalTo(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA));

    final byte[] source3 = HexDecoder.decodeHex("62234F07A00000005555554F07A0000000555555570F1234567890123458D191220112345F");

    final DuplicateTagException duplicateTagException = catchThrowableOfType(() -> DecoderCpm.decode(source3, CommonDataTemplate.class), DuplicateTagException.class);

    assertThat(duplicateTagException.getMessage(), equalTo("Scope: 'CommonDataTemplate' informed already contains '4F' tag"));
    assertThat(duplicateTagException.getTag(), equalTo("4F"));
    assertThat(duplicateTagException.getValue(), equalTo("4F07A0000000555555"));

  }

  @Test
  public void testSuccessDecodeEncode() throws PresentedModeException, IOException {
    final byte[] source = HexDecoder.decodeHex("621A4F07A0000000555555570F1234567890123458D191220112345F");
    final CommonDataTemplate commonDataTemplate = DecoderCpm.decode(source, CommonDataTemplate.class);
    assertThat(commonDataTemplate.getBytes(), equalTo(source));
  }

}
