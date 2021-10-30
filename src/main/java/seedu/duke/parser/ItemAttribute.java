package seedu.duke.parser;

import java.util.HashMap;
import java.util.Map;

public enum ItemAttribute {
    TITLE("title", "n/"),
    DATE("date", "d/"),
    VENUE("venue", "v/"),
    BUDGET("budget", "b/");

    private static final Map<ItemAttribute, String> BY_ATTRIBUTE_NAME = new HashMap<>();
    private static final Map<ItemAttribute, String> BY_ATTRIBUTE_FLAG = new HashMap<>();

    static {
        for (ItemAttribute itemAttribute : values()) {
            BY_ATTRIBUTE_NAME.put(itemAttribute, itemAttribute.attributeName);
            BY_ATTRIBUTE_FLAG.put(itemAttribute, itemAttribute.attributeFlag);
        }
    }

    private final String attributeName;
    private final String attributeFlag;

    ItemAttribute(String attributeName, String attributeFlag) {
        this.attributeName = attributeName;
        this.attributeFlag = attributeFlag;
    }

    public static String getAttributeName(ItemAttribute itemAttribute) {
        return BY_ATTRIBUTE_NAME.get(itemAttribute);
    }

    public static String getItemFlag(ItemAttribute itemAttribute) {
        return BY_ATTRIBUTE_FLAG.get(itemAttribute);
    }
}
