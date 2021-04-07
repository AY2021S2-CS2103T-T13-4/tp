package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.appointment.Date.MESSAGE_DATE_OVER;
import static seedu.address.model.appointment.Time.MESSAGE_TIME_OVER;
import static seedu.address.model.property.Deadline.MESSAGE_DEADLINE_OVER;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.property.Deadline;
import seedu.address.model.remark.Remark;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object.
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_REMARK, PREFIX_DATE, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_REMARK, PREFIX_DATE, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        Date date = ParserUtil.parseAppointmentDate(argMultimap.getValue(PREFIX_DATE).get());
        Time time = ParserUtil.parseAppointmentTime(argMultimap.getValue(PREFIX_TIME).get());

        if (isAppointmentDateOver(date)) {
            throw new ParseException(MESSAGE_DATE_OVER);
        }
        if (isAppointmentTimeOver(time)) {
            throw new ParseException(MESSAGE_TIME_OVER);
        }

        Appointment appointment = new Appointment(name, remark, date, time);

        return new AddAppointmentCommand(appointment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isAppointmentDateOver(Date date) {
        return date.compareTo(new Date(LocalDate.now())) < 0;
    }

    private static boolean isAppointmentTimeOver(Time time) {
        return time.compareTo(new Time(LocalTime.now())) < 0;
    }
}
