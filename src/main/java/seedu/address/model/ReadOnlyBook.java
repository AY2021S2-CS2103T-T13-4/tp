package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyBook<T> {

    /**
     * Returns an unmodifiable view of the object list.
     * This list will not contain any duplicate objects.
     */
    ObservableList<T> getReadOnlyList();

}
