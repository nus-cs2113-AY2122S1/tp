package expiryeliminator.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void toTitleCase_lowercaseString_success() {
        assertEquals("Lorem Ipsum Dolor Sit Amet", Utils.toTitleCase("lorem ipsum dolor sit amet"));
        assertEquals("Hello World", Utils.toTitleCase("hello world"));
        assertEquals("Abc", Utils.toTitleCase("abc"));
    }

    @Test
    public void toTitleCase_uppercaseString_success() {
        assertEquals("Lorem Ipsum Dolor Sit Amet", Utils.toTitleCase("LOREM IPSUM DOLOR SIT AMET"));
        assertEquals("Hello World", Utils.toTitleCase("HELLO WORLD"));
        assertEquals("Abc", Utils.toTitleCase("ABC"));
    }

    @Test
    public void toTitleCase_titleCaseString_success() {
        assertEquals("Lorem Ipsum Dolor Sit Amet", Utils.toTitleCase("Lorem Ipsum Dolor Sit Amet"));
        assertEquals("Hello World", Utils.toTitleCase("Hello World"));
        assertEquals("Abc", Utils.toTitleCase("Abc"));
    }

    @Test
    public void toTitleCase_messyCaseString_success() {
        assertEquals("Lorem Ipsum Dolor Sit Amet", Utils.toTitleCase("lOrEm iPsUm dOlOr sIt aMeT"));
        assertEquals("Hello World", Utils.toTitleCase("hElLo wOrLd"));
        assertEquals("Abc", Utils.toTitleCase("aBc"));
    }

    @Test
    public void toTitleCase_stringWithSpaces_success() {
        assertEquals("Lorem Ipsum Dolor Sit Amet",
                Utils.toTitleCase("   Lorem   Ipsum   Dolor   Sit   Amet   "));
        assertEquals("Hello World", Utils.toTitleCase("   Hello   World   "));
        assertEquals("Abc", Utils.toTitleCase("   Abc   "));
    }

    @Test
    public void toTitleCase_emptyString() {
        assertEquals("", Utils.toTitleCase(""));
    }
}
