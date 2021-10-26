package seedu.utility;

import java.util.ArrayList;
import java.util.Random;

public class FinancialAdvisor {
    private ArrayList<String> financialAdvices;
    private Random random;
    
    public FinancialAdvisor() {
        this.financialAdvices = new ArrayList<>();
        this.random = new Random();
    }

    public void addAdvice(String advice) {
        financialAdvices.add(advice);
    }
    
    public String getRandomAdvice() {
        int bound = financialAdvices.size();
        if (bound > 0) {
            int adviceIndex = random.nextInt(bound);
            return financialAdvices.get(adviceIndex);
        }
        return Messages.DISPLAY_ADVICE_ERROR;
    }
}
