package seedu.cardli.testing;

import seedu.cardli.exceptions.EmptyDeckException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.TestParser;
import seedu.cardli.ui.TestUi;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.FlashCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.cardli.ui.TestUi.END_REVIEW_MESSAGE;
import static seedu.cardli.ui.TestUi.END_TEST_MESSAGE;
import static seedu.cardli.ui.TestUi.INCORRECT_INPUT_FORMAT_MESSAGE;
import static seedu.cardli.ui.TestUi.NO_CARDS_TO_REVIEW_MESSAGE;
import static seedu.cardli.ui.TestUi.NO_CARDS_TO_TEST_MESSAGE;
import static seedu.cardli.ui.TestUi.TIMES_UP_MESSAGE;

/**
 * Implements the test function.
 */
public class TestManager {

    private static final int TIME_PER_QUESTION = 15;

    private static final String EMPTY_ANSWER = "";

    private final TestUi ui;
    private final Logger logger = Logger.getLogger(TestManager.class.getName());
    private final TestHistory testHistory;
    private final DeckManager deckManager;

    public TestManager(TestHistory testHistory, DeckManager deckManager) {
        this.logger.setLevel(Level.WARNING);
        this.testHistory = testHistory;
        this.deckManager = deckManager;
        this.ui = new TestUi();
    }

    public TestManager(TestHistory testHistory, DeckManager deckManager, TestUi ui) {
        this.logger.setLevel(Level.WARNING);
        this.testHistory = testHistory;
        this.deckManager = deckManager;
        this.ui = ui;
    }

    /**
     * Enters test mode and requires user to input the index of the deck that they want to be tested.
     * If the input is "all", all decks will be tested. If the input is an integer, the deck at
     * that index will be tested.
     *
     * @return end test message
     */
    public String startTest() {
        logger.setLevel(Level.SEVERE);
        logger.log(Level.INFO, "starting test");
        ui.printStartTest();
        String input = ui.getUserMessage();
        try {
            logger.log(Level.INFO, "choosing deck to test");
            int deckIndex = TestParser.toInt(input);

            Deck deckToTest = deckManager.getTestDeck(deckIndex);
            AnswerList userAnswers = new AnswerList(deckToTest);

            ArrayList<FlashCard> shuffledDeck = prepareTestDeck(userAnswers);
            testInProgress(shuffledDeck, userAnswers);

            markTest(userAnswers);
            testHistory.addAnswerList(userAnswers);
            return END_TEST_MESSAGE;
        } catch (NumberFormatException e) {
            ui.showMessage(INCORRECT_INPUT_FORMAT_MESSAGE);
            logger.log(Level.WARNING, "Incorrect format causing NumberFormatException");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(e.getMessage());
            logger.log(Level.WARNING, "Deck does not exist causing IndexOutOfBoundsException");
        } catch (EmptyDeckException e) {
            ui.showMessage(e.getMessage());
            logger.log(Level.WARNING, "Empty deck");
        }
        return EMPTY_ANSWER;
    }

    /**
     * Enters review mode and requires user to input the index of the deck that they want to be reviewed.
     * If the input is "all", the cards will come from all decks. If the input is an integer, only cards from
     * the deck at that index will be tested.
     *
     * @return end review message
     */
    public String startReview() {
        logger.setLevel(Level.SEVERE);
        logger.log(Level.INFO, "starting review");
        ui.printStartReview();
        String input = ui.getUserMessage();
        try {
            logger.log(Level.INFO, "choosing deck to test");
            int deckIndex = TestParser.toInt(input);
            Deck deckToReview = deckManager.getLowScoringCards(deckIndex);

            logger.log(Level.INFO, "Reviewing low scoring cards");
            AnswerList answerList = new AnswerList(deckToReview);

            ArrayList<FlashCard> shuffledDeck = prepareTestDeck(answerList);
            testInProgress(shuffledDeck, answerList);

            markTest(answerList);
            testHistory.addAnswerList(answerList);

            return END_REVIEW_MESSAGE;
        } catch (NumberFormatException e) {
            ui.showMessage(INCORRECT_INPUT_FORMAT_MESSAGE);
            logger.log(Level.WARNING, "Incorrect format causing NumberFormatException");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage(e.getMessage());
            logger.log(Level.WARNING, "Incorrect format causing IndexOutOfBoundsException");
        } catch (EmptyDeckException e) {
            ui.showMessage(NO_CARDS_TO_REVIEW_MESSAGE);
        }
        return EMPTY_ANSWER;
    }

    //@@author ThaddeusLim99

    /**
     * Shuffles the test deck and initializes the AnswerList with empty Answers.
     *
     * @param userAnswer user's answers
     * @return shuffled test deck
     * @throws EmptyDeckException if test deck is empty
     */
    public ArrayList<FlashCard> prepareTestDeck(AnswerList userAnswer) throws EmptyDeckException {
        ArrayList<FlashCard> deckReplicate = userAnswer.getDeck().getCards();
        if (deckReplicate.isEmpty()) {
            throw new EmptyDeckException(NO_CARDS_TO_TEST_MESSAGE);
        }
        Collections.shuffle(deckReplicate);
        logger.log(Level.INFO, "replicated and shuffled flashcard list");
        logger.log(Level.INFO, "populating userAnswer");
        //populate userAnswer
        for (FlashCard question : deckReplicate) {
            int questionNumber = userAnswer.getDeck().getCardIndex(question);
            userAnswer.addAnswer(EMPTY_ANSWER, questionNumber);
        }
        return deckReplicate;
    }

    /**
     * Iterates through the shuffled deck for the user to answer.
     * The user's answer is saved into an AnswerList.
     *
     * @param deckReplicate shuffled test deck
     * @param userAnswer    user's answers
     */
    private void testInProgress(ArrayList<FlashCard> deckReplicate, AnswerList userAnswer) {
        boolean allQuestionsAnswered = false;
        int currentQuestion = 0;
        int nextQuestionFlag = 0;
        int numOfQuestions = deckReplicate.size();
        int timer = numOfQuestions * TIME_PER_QUESTION;
        Countdown countdown = new Countdown(timer, TIMES_UP_MESSAGE);

        logger.log(Level.INFO, "starting test proper");
        countdown.start();
        while (!allQuestionsAnswered && countdown.isRunning()) {
            logger.log(Level.INFO, "currentQuestion is out of index. Either test finished or user scroll too far");
            while (currentQuestion >= 0 && currentQuestion < deckReplicate.size() && countdown.isRunning()) {
                //question is not answered yet
                if (!userAnswer.isQuestionAnswered(currentQuestion)) {
                    logger.log(Level.INFO, "question not answered yet");
                    nextQuestionFlag = testCard(userAnswer, deckReplicate.get(currentQuestion), countdown);
                }
                logger.log(Level.INFO, "setting next question to test");
                //next question to be tested is currentQuestion - 1
                if (nextQuestionFlag == 1) {
                    currentQuestion--;
                } else {
                    //next question to be tested is currentQuestion + 1
                    currentQuestion++;
                }
            }
            logger.log(Level.INFO, "Wraparound for edge case");
            //wraparound from end of deckReplicate to start of deckReplicate
            if (currentQuestion == deckReplicate.size()) {
                currentQuestion = 0;
            }
            //wraparound from start of deckReplicate to end of deckReplicate
            if (currentQuestion == -1) {
                currentQuestion = deckReplicate.size() - 1;
            }
            logger.log(Level.INFO, "checking isAllAnswered");
            if (userAnswer.isAllAnswered()) {
                allQuestionsAnswered = true;
            }
        }
        if (countdown.isRunning()) {
            countdown.stop();
        }
        ui.clearScreen();
        ui.printDividerLine();
        logger.log(Level.INFO, "Finished test");
        //let user know testing is over
        ui.printTestOver();
    }

    private int testCard(AnswerList userAnswer, FlashCard question, Countdown countdown) {
        logger.log(Level.INFO, "starting to test a new card");

        //0 means proceed to next question in userAnswer;1 means go back 1 question
        int nextQuestionFlag = 0;

        int questionNumber = userAnswer.getDeck().getCardIndex(question);

        ui.clearScreen();
        ui.printDividerLine();
        ui.printQuestion(question, questionNumber);

        //get user's answer to the card shown(currently assume user inputs only his/her answer)
        //later version to include question number and parsing to allow for randomised testing
        logger.log(Level.INFO, "getting user's answer to the question");

        String userResponse = ui.getUserMessage();

        try {
            userResponse = TestParser.parseUserResponse(userResponse);
        } catch (FieldEmptyException e) {
            logger.log(Level.INFO, "No user input");
            userResponse = EMPTY_ANSWER;
            ui.printAnswerEmptyError();
        }

        if (!countdown.isRunning()) {
            // timer has run out
            userResponse = EMPTY_ANSWER;
        }

        //set question as answered with the new user response
        if (!(userResponse.trim().equalsIgnoreCase("/NEXT") || userResponse.trim().equalsIgnoreCase("/BACK"))) {
            logger.log(Level.INFO, "Saving answer");
            userAnswer.setQuestionAnswer(questionNumber, userResponse);
            userAnswer.getAnswer(questionNumber).setIsAnswered();
        }
        //signalling to test previous question next
        if (userResponse.trim().equalsIgnoreCase("/BACK")) {
            nextQuestionFlag = 1;
        }

        assert !userAnswer.isEmpty();
        assert userAnswer.getSize() > 0;
        logger.log(Level.INFO, "Finished this card's testing");

        return nextQuestionFlag;
    }
    //@@author xRossKoh

    /**
     * Marks the user's answers then print their results of test to system output.
     */
    public void markTest(AnswerList userAnswers) {
        logger.log(Level.INFO, "starting test check");

        //there must be at least one response to start a test
        assert userAnswers.getSize() > 0;
        for (Answer response : userAnswers.getAnswerList()) {
            markQuestion(userAnswers, response);
        }
        ui.printDividerLine();
        int answersCount = userAnswers.getSize();
        int score = userAnswers.getUserScore();
        assert score <= answersCount;
        System.out.println("You scored " + score + " out of " + answersCount + " for this test.");
        System.out.println("That is " + Math.round(((double) score / answersCount) * 10000) / 100 + "%!");
        logger.log(Level.INFO, "all answers checked, score printed to system output");
    }

    // Marks the user's answer
    private void markQuestion(AnswerList userAnswers, Answer response) {
        int responseNumber = userAnswers.getAnswerIndex(response);
        FlashCard question = userAnswers.getDeck().getCard(responseNumber);
        String userAnswer = response.getAnswer();

        ui.printDividerLine();
        //display front of card so that user can understand question
        ui.printQuestion(question, responseNumber);
        ui.printCorrectAnswer(question);
        ui.printUserAnswer(userAnswer);

        if (response.isCorrect(userAnswer, question)) {
            userAnswers.incrementUserScore();
            question.incrementUserScore();
            ui.printCorrectAnsMessage();
            logger.log(Level.INFO, "user answer is correct");
        } else {
            ui.printWrongAnsMessage();
            logger.log(Level.INFO, "user answer is wrong");
        }
        question.incrementTotalScore();
    }
}
