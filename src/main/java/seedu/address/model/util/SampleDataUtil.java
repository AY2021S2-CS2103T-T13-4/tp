package seedu.address.model.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.*;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[]{
                new Appointment(new Name("Meet Alex"),
                        new Remark("At M Hotel"),
                        new Date(LocalDate.parse("25-12-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                        new Time(LocalTime.parse("1500", DateTimeFormat.INPUT_TIME_FORMAT))),
                new Appointment(new Name("Meet Bob"),
                        new Remark("At Plaza Singapore Starbucks"),
                        new Date(LocalDate.parse("01-02-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                        new Time(LocalTime.parse("2000", DateTimeFormat.INPUT_TIME_FORMAT))),
                new Appointment(new Name("Meet Alice"),
                        new Remark("At client's house"),
                        new Date(LocalDate.parse("17-08-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                        new Time(LocalTime.parse("1500", DateTimeFormat.INPUT_TIME_FORMAT))),
                new Appointment(new Name("Meet Caleb"),
                        new Remark("At void deck of his house"),
                        new Date(LocalDate.parse("07-03-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                        new Time(LocalTime.parse("1030", DateTimeFormat.INPUT_TIME_FORMAT))),
                new Appointment(new Name("Meet Simon"),
                        new Remark("At Queenstown MRT station"),
                        new Date(LocalDate.parse("20-09-2021", DateTimeFormat.INPUT_DATE_FORMAT)),
                        new Time(LocalTime.parse("1200", DateTimeFormat.INPUT_TIME_FORMAT)))
        };
    }

    /**
     * Returns an appointment book containing some sample appointments.
     */
    public static ReadOnlyBook<Appointment> getSampleAppointmentBook() {
        AppointmentBook sampleAppointmentBook = new AppointmentBook();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAppointmentBook.addAppointment(sampleAppointment);
        }
        return sampleAppointmentBook;
    }
}
