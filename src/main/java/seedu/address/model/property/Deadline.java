package seedu.address.model.property;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.property.util.DateFormat;

/**
 * Represents a Property's deadline for selling.
 * Guarantees: immutable.
 */
public class Deadline {
    private static final String MESSAGE_CONSTRAINTS =
            "Deadline dates should be valid dates specified in the format DD-MM-YY.";

    public final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline date.
     */
    public Deadline(LocalDate deadline) {
        requireNonNull(deadline);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return deadline.format(DateFormat.OUTPUT_DATE_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Deadline)) {
            return false;
        }
        Deadline otherDeadline = (Deadline) other;
        return deadline.equals(otherDeadline.deadline);
    }

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
