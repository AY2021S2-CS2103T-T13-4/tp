package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Person;

/**
 * Wraps all data at the appointment-book level.
 * Duplicates are not allowed (by .isSameAppointment comparison).
 */
public class AppointmentBook implements ReadOnlyBook<Appointment> {
    private final UniqueAppointmentList appointments;

    public AppointmentBook() {
        appointments = new UniqueAppointmentList();
    }

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the app.
     *
     * @param appointment The appointment to check.
     * @return True if there is an appointment with the same identity, otherwise false.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the app.
     * The appointment must not already exist in the appointment book.
     *
     * @param appointment The appointment to be added.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    @Override
    public String toString() {
        return appointments.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Appointment> getReadOnlyList() {
        return appointments.asUnmodifiableObservableList();
    }

}
