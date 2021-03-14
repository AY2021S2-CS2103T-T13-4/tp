package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.property.Property;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    //TODO remove this eventually
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;



    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' appointment book file path.
     */
    Path getAppointmentBookFilePath();

    /**
     * Sets the user prefs' appointment book file path.
     * @param appointmentBookFilePath path to file containing appointment book
     */
    void setAppointmentBookFilePath(Path appointmentBookFilePath);

    /**
     * Replaces appointment book data with the data in {@code appointmentBook}.
     * @param appointmentBook unmodifiable list of appointments
     */
    void setAppointmentBook(ReadOnlyBook<Appointment> appointmentBook);

    /** Returns the AppointmentBook
     * @return unmodifiable list of appointments
     * */
    ReadOnlyBook<Appointment> getAppointmentBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // ======================================================================================================
    // API for PropertyBook

    boolean hasProperty(Property property);

    void addProperty(Property property);

    // ======================================================================================================
    // API for AppointmentBook


    boolean hasAppointment(Appointment appointment);

    void addAppointment(Appointment appointment);

    /** Returns an unmodifiable view of the filtered appointments list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointments list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);
}
