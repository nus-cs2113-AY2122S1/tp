package seedu.cardli.commands.deck;

import seedu.cardli.commands.Command;
import seedu.cardli.commands.CommandResult;
import seedu.cardli.commands.system.EditDeckCommand;
import seedu.cardli.exceptions.CardLiException;
import seedu.cardli.exceptions.FieldEmptyException;
import seedu.cardli.exceptions.InvalidCommandFormatException;
import seedu.cardli.flashcard.Deck;
import seedu.cardli.flashcard.DeckManager;
import seedu.cardli.parser.deck.MoveCardParser;
import seedu.cardli.testing.TestManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements the MoveCardCommand class, which moves the specified card in
 * the current deck to another deck.
 */
public class MoveCardCommand extends Command {

    private static final String FIELD_EMPTY_ERROR_MESSAGE = "You cannot leave any field empty! "
            + "Format should be\nmove /c <card index> /d <deck index>";
    private static final String WRONG_ORDER_ERROR_MESSAGE = "/c should come before /d! Format should be\n"
            + "move /c <card index> /d <deck index>";
    private static final String SAME_DESTINATION_ERROR_MESSAGE = "Your card is already in the deck specified!";
    private static final String LARGE_INTEGER_ERROR_MESSAGE = "Card and Deck index must be both "
            + "smaller than 2147483647.";
    private static final String INVALID_ARGUMENTS_MESSAGE = "Please use the correct flags and in the correct order! "
            + "\nFormat should be move /c <card index> /d <deck index>";
    private static final String FLAG_ARGUMENT_ERROR_MESSAGE = "You should not use this command's flag as your argument";
    private static final String MISSING_FLAG_MESSAGE = "You are missing the relevant flag/flags";

    private MoveCardParser parser;
    private Deck deck;
    private DeckManager deckManager;
    private static Logger logger = Logger.getLogger(TestManager.class.getName());

    public MoveCardCommand(String arguments, Deck deck, DeckManager deckManager) {
        super("MoveCardCommand", arguments);
        this.deck = deck;
        this.deckManager = deckManager;
        this.parser = new MoveCardParser();
    }

    /**
     * Returns the arguments for MoveCardCommand if accepted.
     *
     * @return accepted arguments.
     * @throws FieldEmptyException If arguments or flags are empty.
     * @throws InvalidCommandFormatException If flags are in the wrong position.
     * @throws seedu.cardli.exceptions.DeckNotExistException If the deck index given is out of bounds.
     * @throws NumberFormatException If an integer above 2147483647 is entered by the user as the card or deck index.
     * @throws CardLiException If flags are used as arguments,if a card is being sent to the deck it is currently in,
     *      if a non-integer is given as index, if the card index given is out of bounds.
     */
    public String[] prepareMoveCardCommand() throws CardLiException {
        logger.setLevel(Level.WARNING);
        logger.log(Level.INFO, "preparing MoveCommand");

        if (arguments.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "Checking if input contains /d and /c");
        if (!arguments.contains("/c") || !arguments.contains("/d")) {
            throw new FieldEmptyException(MISSING_FLAG_MESSAGE);
        }

        logger.log(Level.INFO, "Checking if /d and /c are in the right order");
        if (!(arguments.indexOf("/c") < arguments.indexOf("/d"))) {
            throw new InvalidCommandFormatException(WRONG_ORDER_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "Splitting the input up");
        String[] rawParameters = parser.parseArguments(super.arguments);

        logger.log(Level.INFO, "Checking if there is enough arguments");
        if (rawParameters.length != 4) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "Checking if /c,/d are in the right order");
        if (!(rawParameters[0].trim().equals("/c") && rawParameters[2].trim().equals("/d"))) {
            throw new InvalidCommandFormatException(INVALID_ARGUMENTS_MESSAGE);
        }

        String cardInput = rawParameters[1].trim();
        String deckInput = rawParameters[3].trim();

        logger.log(Level.INFO, "Checking if any field is empty");
        if (cardInput.isEmpty() || deckInput.isEmpty()) {
            throw new FieldEmptyException(FIELD_EMPTY_ERROR_MESSAGE);
        }

        if (cardInput.equalsIgnoreCase("/c") || cardInput.equalsIgnoreCase("/d")
                || deckInput.equalsIgnoreCase("/c") || deckInput.equalsIgnoreCase("/d")) {
            throw new CardLiException(FLAG_ARGUMENT_ERROR_MESSAGE);
        }

        logger.log(Level.INFO, "preparing CardIndex and DeckIndex");
        cardInput = EditCardCommand.prepareCardIndex(cardInput, deck);
        deckInput = EditDeckCommand.prepareDeckIndex(deckInput, deckManager);

        logger.log(Level.INFO, "checking if user is already in specified deck");
        if ((Integer.parseInt(deckInput) - 1) == deckManager.getDeckIndex(deck)) {
            throw new CardLiException(SAME_DESTINATION_ERROR_MESSAGE);
        }

        String sourceDeckIndex = String.valueOf(deckManager.getDeckIndex(deck));

        String[] preparedArguments = {sourceDeckIndex, cardInput, deckInput};

        return preparedArguments;
    }

    @Override
    public CommandResult execute() {
        CommandResult result;
        try {
            String[] parameters = prepareMoveCardCommand();
            result = new CommandResult(deckManager.moveCard(parameters));
        } catch (CardLiException e) {
            result = new CommandResult(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            result = new CommandResult("You are moving the card to a deck that does not exist.");
        } catch (NumberFormatException e) {
            result = new CommandResult(LARGE_INTEGER_ERROR_MESSAGE);
        }
        return result;
    }
}

