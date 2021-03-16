package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTING_ORDER;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.sort.descriptor.AppointmentSortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;

/**
 * Adds an appointment to the app.
 */
public class SortAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "sort appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the appointment list based on the sorting key.\n"
            + "Parameters: "
            + PREFIX_SORTING_ORDER + "SORTING ORDER "
            + PREFIX_SORTING_KEY + "SORTING KEY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORTING_ORDER + "des "
            + PREFIX_SORTING_KEY + "datetime ";

    public static final String MESSAGE_SUCCESS = "Appointment list sorted in %1$s order by %2$s";

    private static final Supplier<CommandException> invalidCommandExceptionSupplier = () -> new CommandException(
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));

    private final SortAppointmentCommand.SortAppointmentDescriptor sortAppointmentDescriptor;

    /**
     * Creates an SortAppointmentCommand to sort appointment list based on the information in {@code Appointment}.
     */
    public SortAppointmentCommand(SortAppointmentCommand.SortAppointmentDescriptor sortAppointmentDescriptor) {
        requireNonNull(sortAppointmentDescriptor);

        this.sortAppointmentDescriptor = new SortAppointmentCommand
                .SortAppointmentDescriptor(sortAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Comparator<Appointment> cmp = createAppointmentComparator(sortAppointmentDescriptor);
        model.sortAppointmentList(cmp);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                sortAppointmentDescriptor.getSortingOrder().orElseThrow(invalidCommandExceptionSupplier),
                sortAppointmentDescriptor.getAppointmentSortingKey().orElseThrow(invalidCommandExceptionSupplier)));
    }

    /**
     * Creates and returns an {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code sortAppointmentDescriptor}.
     */
    private static Comparator<Appointment> createAppointmentComparator(
            SortAppointmentDescriptor sortAppointmentDescriptor) throws CommandException {
        assert sortAppointmentDescriptor != null;

        SortingOrder sortingOrder = sortAppointmentDescriptor.getSortingOrder().orElse(
                SortAppointmentCommand.SortAppointmentDescriptor.DEFAULT_SORTING_ORDER);

        AppointmentSortingKey appointmentSortingKey = sortAppointmentDescriptor.getAppointmentSortingKey()
                .orElseThrow(invalidCommandExceptionSupplier);

        return (Appointment o1, Appointment o2) -> {
            if (appointmentSortingKey.isName()) {
                int result = o1.getName().compareTo(o2.getName());
                if (sortingOrder.isAscendingOrder()) {
                    return result;
                } else {
                    return -1 * result;
                }
            } else {
                int result = o1.getDate().compareTo(o2.getDate());
                if (result == 0) {
                    result = o1.getTime().compareTo(o2.getTime());
                }

                if (sortingOrder.isAscendingOrder()) {
                    return result;
                } else {
                    return -1 * result;
                }
            }
        };
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortAppointmentCommand // instanceof handles nulls
                && sortAppointmentDescriptor.equals(((SortAppointmentCommand) other).sortAppointmentDescriptor));
    }

    public static class SortAppointmentDescriptor {
        public static final SortingOrder DEFAULT_SORTING_ORDER = new SortingOrder("asc");
        private SortingOrder sortingOrder;
        private AppointmentSortingKey appointmentSortingKey;

        public SortAppointmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public SortAppointmentDescriptor(SortAppointmentCommand.SortAppointmentDescriptor toCopy) {
            setSortingOrder(toCopy.sortingOrder);
            setAppointmentSortingKey(toCopy.appointmentSortingKey);
        }

        public void setSortingOrder(SortingOrder sortingOrder) {
            this.sortingOrder = sortingOrder;
        }

        public Optional<SortingOrder> getSortingOrder() {
            return Optional.ofNullable(sortingOrder);
        }

        public void setAppointmentSortingKey(AppointmentSortingKey appointmentSortingKey) {
            this.appointmentSortingKey = appointmentSortingKey;
        }

        public Optional<AppointmentSortingKey> getAppointmentSortingKey() {
            return Optional.ofNullable(appointmentSortingKey);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SortAppointmentCommand.SortAppointmentDescriptor)) {
                return false;
            }

            // state check
            SortAppointmentCommand.SortAppointmentDescriptor e =
                    (SortAppointmentCommand.SortAppointmentDescriptor) other;

            return getSortingOrder().equals(e.getSortingOrder())
                    && getAppointmentSortingKey().equals(e.getAppointmentSortingKey());
        }
    }
}
