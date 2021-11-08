package terminus.common;

public class TestUtils {
    
    public static String generateCommandOutputString(String[] messages) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String message: messages) {
            stringBuilder.append(message);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString().trim();
    }

}
