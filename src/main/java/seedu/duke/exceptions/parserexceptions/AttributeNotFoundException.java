package seedu.duke.exceptions.parserexceptions;

import seedu.duke.parser.ItemAttribute;

public class AttributeNotFoundException extends Exception {

    private final ItemAttribute itemAttribute;

    public AttributeNotFoundException(ItemAttribute itemAttribute) {
        super();
        this.itemAttribute = itemAttribute;
    }

    public ItemAttribute getItemAttribute() {
        return itemAttribute;
    }
}
