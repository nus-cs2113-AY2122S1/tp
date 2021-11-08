package decodex.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataTest {

    @Test
    public void getRawBytes_stringConstructor_actualBytesOfString() {
        Data testData = new Data("hi");
        byte[] expectedBytes = new byte[]{104, 105}; // ASCII values of "hi"
        assertArrayEquals(testData.getRawBytes(), expectedBytes);
    }

    @Test
    public void getRawBytes_bytesConstructor_actualBytesOfString() {
        byte[] expectedBytes = new byte[]{104, 105}; // ASCII values of "hi"
        Data testData = new Data(expectedBytes);
        assertArrayEquals(testData.getRawBytes(), expectedBytes);
    }

    @Test
    public void toString_correctStringRepresentation() {
        String testString = "hi";
        Data testData = new Data(testString);
        assertEquals(testString, testData.toString());
    }
}
