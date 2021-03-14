package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBook;
import seedu.address.model.appointment.Appointment;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface AppointmentBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAppointmentBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyBook<Appointment>}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBook<Appointment>> readAppointmentBook() throws DataConversionException, IOException;

    /**
     * @see #getAppointmentBookFilePath()
     */
    Optional<ReadOnlyBook<Appointment>> readAppointmentBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBook<Appointment>} to the storage.
     * @param appointmentBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAppointmentBook(ReadOnlyBook<Appointment> appointmentBook) throws IOException;

    /**
     * @see #saveAppointmentBook(ReadOnlyBook)
     */
    void saveAppointmentBook(ReadOnlyBook<Appointment> appointmentBook, Path filePath) throws IOException;

}
