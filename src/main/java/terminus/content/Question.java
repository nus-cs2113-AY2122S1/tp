package terminus.content;

import terminus.activerecall.DifficultyModifier;

public class Question extends Content {
    
    private double weight;

    public Question(String question, String answer) {
        super(question, answer);
        this.weight = 0.5;
    }
    
    public String getQuestion() {
        return this.name;
    }
    
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
    
    public double getWeight() {
        setWeightSafely();
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        setWeightSafely();
    }
}
