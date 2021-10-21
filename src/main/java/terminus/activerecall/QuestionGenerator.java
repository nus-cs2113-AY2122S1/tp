package terminus.activerecall;

import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import terminus.content.Question;

public class QuestionGenerator {
    
    private final NavigableMap<Double, Question> questionBank = new TreeMap<>();
    private final Random random;
    private double total = 0;
    private int questionCount;

    /**
     * Instantiates a QuestionGenerator.
     *
     * @param questionBank The list of questions to pick from.
     * @param questionCount The maximum number of questions to pull.
     */
    public QuestionGenerator(List<Question> questionBank, int questionCount) {
        this(questionBank, questionCount, new Random());
    }

    /**
     * Instantiates a QuestionGenerator with a specific Random generator.
     * 
     * @param questionBank The list of questions to pick from.
     * @param questionCount The maximum number of questions to pull.
     * @param random The random generator to determine the order of questions.
     */
    public QuestionGenerator(List<Question> questionBank, int questionCount, Random random) {
        this.questionCount = questionCount;
        this.random = random;
        questionBank.forEach(this::addQuestion);
    }
    
    private void addQuestion(Question question) {
        assert question.getWeight() > 0;
        total += question.getWeight();
        questionBank.put(total, question);
    }

    /**
     * Gets the size of the question pool.
     * 
     * @return The size of the question pool.
     */
    public int getQuestionPoolSize() {
        return Math.min(questionBank.size(), questionCount);
    }

    /**
     * Checks if there are still questions left in the pool,
     * or if the maximum number of questions have been asked.
     * 
     * @return Return false if there are no questions left, or if the maximum number of questions have been asked. 
     */
    public boolean hasNext() {
        return questionCount > 0 && !(total <= 0) && !questionBank.isEmpty();
    }

    /**
     * Gets the next question from the question pool randomly.
     *
     * @return The next question from the question pool.
     * @throws NullPointerException When there are no questions left.
     */
    public Question next() {
        if (!hasNext()) {
            throw new NullPointerException("There are no questions left.");
        }
        double key = random.nextDouble() * total;
        Entry<Double, Question> keyValuePair = questionBank.higherEntry(key);
        Question question = keyValuePair.getValue();
        
        questionBank.remove(keyValuePair.getKey(), question);
        total -= question.getWeight();
        questionCount -= 1;
        
        return question;
    }
}
