package terminus.activerecall;

public class DifficultyModifier {
    
    public static final double MIN_VALUE = 0.2;
    public static final double MAX_VALUE = 0.9;
    private static final double MAX_INCREASE = 0.45;
    private static final double GROWTH_RATE = 8;
    private static final double LOG_CURVE_MIDPOINT = 0.4;

    /**
     * Get the value to increment or decrement by based on the logistic curve.
     * 
     * @param x The difficulty difference
     * @return The increment or decrement to apply to the question randomness weight.
     */
    private static double getCurveValue(double x) {
        double exponentValue = -1 * DifficultyModifier.GROWTH_RATE * (x - DifficultyModifier.LOG_CURVE_MIDPOINT);
        double denominator = (1 + Math.exp(exponentValue));
        return DifficultyModifier.MAX_INCREASE / denominator;
    }

    /**
     * Get the new randomness weightage of the question if the question is deemed easy.
     *
     * @param initial The current randomness weightage of the question
     * @return The new randomness weightage of the question
     */
    public static double tweakEasyQuestionDifficulty(double initial) {
        if (initial <= MIN_VALUE) {
            return MIN_VALUE;
        }
        double difference = initial - MIN_VALUE;
        return Math.max(MIN_VALUE, initial - getCurveValue(difference));
    }

    /**
     * Get the new randomness weightage of the question if the question is deemed hard.
     * 
     * @param initial The current randomness weightage of the question
     * @return The new randomness weightage of the question
     */
    public static double tweakHardQuestionDifficulty(double initial) {
        if (initial >= MAX_VALUE) {
            return MAX_VALUE;
        }
        double difference = MAX_VALUE - initial;
        return Math.min(MAX_VALUE, initial + getCurveValue(difference));
    }
    
}
