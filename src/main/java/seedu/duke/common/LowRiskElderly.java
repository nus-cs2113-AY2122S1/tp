package seedu.duke.common;

import static seedu.duke.common.Messages.TO_STRING_MESSAGE_LOW_RISK_ELDERLY;

public class LowRiskElderly extends Elderly {

    public LowRiskElderly(String username, String name) {
        super(username, name);
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_MESSAGE_LOW_RISK_ELDERLY, super.toString());
    }
}
