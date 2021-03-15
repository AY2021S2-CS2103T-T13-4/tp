package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+(\\s(appointment|property))?)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // To satisfy the condition of "extraneous parameters will be ignored" in command format description
        if (commandWord.startsWith(HelpCommand.COMMAND_WORD)) {
            return new HelpCommand();
        } else if (commandWord.startsWith(ClearAppointmentCommand.COMMAND_WORD)) {
            return new ClearAppointmentCommand();
        } else if (commandWord.startsWith(ListCommand.COMMAND_WORD)) {
            return new ListCommand();
        } else if (commandWord.startsWith(ExitCommand.COMMAND_WORD)) {
            return new ExitCommand();
        }

        switch (commandWord) {

        case AddPropertyCommand.COMMAND_WORD:
            return new AddPropertyCommandParser().parse(arguments);

        case AddAppointmentCommand.COMMAND_WORD:
            return new AddAppointmentCommandParser().parse(arguments);

        case EditPropertyCommand.COMMAND_WORD:
            return new EditPropertyCommandParser().parse(arguments);

        case EditAppointmentCommand.COMMAND_WORD:
            return new EditAppointmentCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DeleteAppointmentCommand.COMMAND_WORD:
            return new DeleteAppointmentCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ClearAppointmentCommand.COMMAND_WORD:
            return new ClearAppointmentCommand();

        case ClearPropertyCommand.COMMAND_WORD:
            return new ClearPropertyCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
