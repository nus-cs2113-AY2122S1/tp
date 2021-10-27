//@@author marcusbory

package seedu.contact;

/**
 * Enumeration for Contact Detail Type.
 * This is used for decoding contacts from CSV. The indexes of CSV file lines
 * upon splitting will be stored here.
 * Eg. ANDRE, ng-andre => upon splitting becomes ["ANDRE", "ng-andre"]
 */
public enum DetailType {
    NAME(0),
    GITHUB(1),
    LINKEDIN(2),
    TELEGRAM(3),
    TWITTER(4),
    EMAIL(5);

    private int index;

    DetailType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
