package terminus.activerecall;

import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import terminus.content.Question;

public class QuestionGenerator {
    
    private final NavigableMap<Double, Question> questionBank = new TreeMap<>();
    private final Random random;
    private double total = 0;
    private int questionCount;

    public QuestionGenerator(List<Question> questionBank, int questionCount) {
        this(questionBank, questionCount, new Random());
    }

    public QuestionGenerator(List<Question> questionBank, int questionCount, Random random) {
        this.questionCount = questionCount;
        this.random = random;
        questionBank.forEach(this::addQuestion);
    }
    
    private void addQuestion(Question question) {
        total += question.getWeight();
        questionBank.put(total, question);
    }
    
    public int getQuestionPool() {
        return Math.min(questionBank.size(), questionCount);
    }
    
    public boolean hasNext() {
        return questionCount > 0 && !(total <= 0) && !questionBank.isEmpty();
    }
    
    public Question next() {
        if (!hasNext()) {
            return null;
        }
        double key = random.nextDouble() * total;
        Question question = questionBank.higherEntry(key).getValue();
        
        questionBank.remove(question.getWeight(), question);
        total -= question.getWeight();
        questionCount -= 1;
        
        return question;
    }
}
