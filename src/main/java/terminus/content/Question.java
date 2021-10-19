package terminus.content;

public class Question extends Content {

    public static final String TYPE = "Q";
    
    private double weight;

    public Question(String question, String answer) {
        super(question, answer);
        this.weight = 0.5;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
