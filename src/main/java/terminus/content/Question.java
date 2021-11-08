package terminus.content;

import terminus.activerecall.DifficultyModifier;

public class Question extends Content {
    
    private double weight;

    public Question(String question, String answer) {
        super(question, answer);
        this.weight = 0.5;
    }

    /**
     * Gets the question data.
     * Equivalent to getName()
     * 
     * @return The question data.
     */
    public String getQuestion() {
        return this.name;
    }

    /**
     * Gets the answer data.
     * Equivalent to getData()
     * 
     * @return The answer data.
     */
    public String getAnswer() {
        return this.data;
    }

    private void setWeightSafely() {
        if (weight < DifficultyModifier.MIN_VALUE) {
            this.weight = DifficultyModifier.MIN_VALUE;
        } else if (weight > DifficultyModifier.MAX_VALUE) {
            this.weight = DifficultyModifier.MAX_VALUE;
        }
    }

    /**
     * Get the randomness weight of the question.
     * 
     * @return The randomness weight of the question.
     */
    public double getWeight() {
        setWeightSafely();
        return weight;
    }

    /**
     * Set the randomness weight of the question.
     * The values are restricted to between DifficultyModifier.MIN_VALUE and DifficultyModifier.MAX_VALUE.
     * 
     * @param weight The randomness weight of the question.
     */
    public void setWeight(double weight) {
        this.weight = weight;
        setWeightSafely();
    }
}
