package seedu.cardli.testing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import seedu.cardli.flashcard.Deck;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains all the user's answers to the tested deck and his score.
 */
public class AnswerList {
    private final ArrayList<Answer> answerList;
    private final Deck deck;
    private int userScore;
    private static final Logger logger = Logger.getLogger(Deck.class.getName());

    /**
     * AnswerList contains the list of answers from a test and the deck
     * the questions came from.
     *
     * @param deck tested deck
     */
    public AnswerList(Deck deck) {
        this.answerList = new ArrayList<>();
        this.deck = deck;
        this.userScore = 0;
    }

    /**
     * Returns true or false regarding if a question is answered.
     *
     * @param index the question in the answerList.
     * @return true or false regarding if a question is answered.
     */
    public Boolean isQuestionAnswered(int index) {
        Boolean isQuestionAnswered;
        try {
            Answer answer = answerList.get(index);
            isQuestionAnswered = answer.isAnswered();
        } catch (IndexOutOfBoundsException e) {
            isQuestionAnswered = false;
        }
        return isQuestionAnswered;
    }

    public void incrementUserScore() {
        userScore++;
    }

    public Boolean isEmpty() {
        return answerList.isEmpty();
    }

    public Boolean isAllAnswered() {
        for (Answer a : answerList) {
            if (!a.isAnswered()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Saves a new user answer to the current list of user answers.
     *
     * @param answer        String representation of user's answer
     * @param questionIndex Question number for the question that the answer answers
     */
    public void addAnswer(String answer, int questionIndex) {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "Adding card");
        answerList.add(new Answer(answer, questionIndex));
    }

    /**
     * Gets the index of the answer in the list.
     *
     * @param answer answer query
     * @return index of the answer
     */
    public int getAnswerIndex(Answer answer) {
        return answerList.indexOf(answer);
    }

    public Answer getAnswer(int questionIndex) {
        return answerList.get(questionIndex);
    }

    public Deck getDeck() {
        return deck;
    }

    public int getSize() {
        return answerList.size();
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void setQuestionAnswer(int questionIndex, String answer) {
        answerList.get(questionIndex).setAnswer(answer);
    }

    @Override
    public String toString() {
        String answersString = "";
        int answersCount = getSize();

        for (int i = 0; i < answersCount; i++) {
            answersString += answerList.get(i);
        }

        return getDeck().toString()
                + answersCount + '\n'
                + answersString
                + getUserScore();

    }

    /**
     * Converts an AnswerList instance into a JSONObject instance.
     *
     * @return      AnswerList instance as a JSONObject instance
     */
    public JSONObject toJsonObject() {
        JSONObject jsonAnswerList = new JSONObject();

        int answerCount = getSize();
        JSONArray jsonAnswers = new JSONArray();

        for (int i = 0; i < answerCount; i++) {
            jsonAnswers.add(answerList.get(i).toJsonObject());
        }
        jsonAnswerList.put("answerList", jsonAnswers);
        jsonAnswerList.put("deck", getDeck().toJsonObject());
        jsonAnswerList.put("userScore", getUserScore());
        return jsonAnswerList;
    }
}
