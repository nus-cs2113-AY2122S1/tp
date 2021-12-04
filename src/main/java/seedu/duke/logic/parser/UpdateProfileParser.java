package seedu.duke.logic.parser;

import seedu.duke.data.profile.utilities.ProfileUtils;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.CommandMessages;
import seedu.duke.logic.commands.InvalidCommand;
import seedu.duke.logic.commands.ProfileCommand;
import seedu.duke.logic.commands.ProfileUpdateCommand;
import seedu.duke.logic.parser.exceptions.ExtraParamException;
import seedu.duke.logic.parser.exceptions.InvalidParamException;
import seedu.duke.logic.parser.exceptions.MissingParamException;
import seedu.duke.logic.parser.exceptions.ParserException;

/**
 * Parses input arguments for Update Profile command.
 */
public class UpdateProfileParser implements Parser {
    @Override
    public Command parse(String params) {
        if (params.trim().isEmpty()) { //no additional parameters, assumed to be view profile command
            return new ProfileCommand();
        }
        if (ParserUtils.hasExtraDelimiters(
                params, ProfileUpdateCommand.EXPECTED_PREFIXES)) {
            return new InvalidCommand(ParserMessages.MESSAGE_ERROR_TOO_MANY_DELIMITERS);
        }
        if (ParserUtils.getNumberOfCorrectParamsDetected(params, ProfileUpdateCommand.EXPECTED_PREFIXES) == 0) {
            return new InvalidCommand(CommandMessages.MESSAGE_PROFILE_COMMAND_INVALID_FORMAT);
        }
        try {
            final String name = ParserUtils.extractName(params);
            final Double height = ParserUtils.extractHeight(params);
            final Double weight = ParserUtils.extractWeight(params);
            final Integer calorieGoal = extractCalorieGoal(params);
            final Integer age = extractAge(params);
            final Integer activityFactor = extractActivityFactor(params);
            final Character gender = extractGender(params);
            return new ProfileUpdateCommand(name, height, weight, calorieGoal, age, activityFactor, gender);
        } catch (ParserException e) {
            return new InvalidCommand(e.getMessage());
        }
    }

    protected static Integer extractCalorieGoal(String params) throws ParserException {
        try {
            return ParserUtils.extractGeneralInteger(params, Command.COMMAND_PREFIX_GOAL);
        } catch (InvalidParamException e) {
            throw new ParserException(ProfileUtils.ERROR_CALORIE_GOAL);
        }
    }

    protected static Integer extractAge(String params) throws ParserException {
        try {
            return ParserUtils.extractGeneralInteger(params, Command.COMMAND_PREFIX_AGE);
        } catch (InvalidParamException e) {
            throw new ParserException(ProfileUtils.ERROR_AGE);
        }
    }

    protected static Integer extractActivityFactor(String params) throws ParserException {
        try {
            return ParserUtils.extractGeneralInteger(params, Command.COMMAND_PREFIX_ACTIVITY_FACTOR);
        } catch (InvalidParamException e) {
            throw new ParserException(ProfileUtils.ERROR_ACTIVITY_FACTOR);
        }
    }

    protected static Character extractGender(String params) throws ParserException {
        try {
            String stringAfterPrefix = ParserUtils.extractRelevantParameterWithoutWhitespace(
                    params, Command.COMMAND_PREFIX_GENDER);
            if (stringAfterPrefix.length() > 1) {
                throw new ParserException(ProfileUtils.ERROR_GENDER);
            }
            return stringAfterPrefix.charAt(0);
        } catch (MissingParamException e) {
            return Command.NULL_CHAR;
        } catch (ExtraParamException e) {
            throw new ParserException(e.getMessage());
        }
    }
}
