package decodex.modules.base64;

import decodex.data.Data;
import decodex.data.exception.ModuleException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Base64DecoderTest {

    private Base64Decoder base64Decoder;

    @BeforeEach
    public void setUpBase64DecoderTest() {
        base64Decoder = new Base64Decoder();
    }

    @Test
    public void run_encodedString_base64DecodedData() throws ModuleException {

        String encodedString = "dGVzdA==";
        String expectedString = "test";

        Data encodedData = new Data(encodedString);
        Data decodedData = base64Decoder.run(encodedData);
        assertEquals(expectedString, decodedData.toString());
    }

    @Test
    public void run_emptyString_emptyDecodedData() throws ModuleException {
        String encodedString = "";
        String expectedString = "";

        Data encodedData = new Data(encodedString);
        Data decodedData = base64Decoder.run(encodedData);
        assertEquals(expectedString, decodedData.toString());
    }

    @Test
    public void run_nullByteBase64EncodedInput_expectNullByte() throws ModuleException {
        String encodedString = "AA==";
        String expectedString = "\00";

        Data encodedData = new Data(encodedString);
        Data decodedData = base64Decoder.run(encodedData);
        assertEquals(expectedString, decodedData.toString());
    }

    @Test
    public void run_stringWithOneSpace_throwsModuleException() {
        String encodedString = " ";

        Data encodedData = new Data(encodedString);
        assertThrows(ModuleException.class, () -> base64Decoder.run(encodedData));
    }

    @Test
    public void run_nullByteString_throwsModuleException() {
        String encodedString = "\00";

        Data encodedData = new Data(encodedString);
        assertThrows(ModuleException.class, () -> base64Decoder.run(encodedData));
    }
}
