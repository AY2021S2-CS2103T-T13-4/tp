package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.name.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Edits a property in the app.
 */
public class EditPropertyCommand extends Command {

    public static final String COMMAND_WORD = "edit property";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a property in the app. \n"
            + "Parameters: INDEX"
            + PREFIX_NAME + "NAME "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_POSTAL + "POSTAL "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_REMARK + "REMARK "
            + PREFIX_CLIENT_NAME + "CLIENT_NAME "
            + PREFIX_CLIENT_CONTACT + "CLIENT_CONTACT "
            + PREFIX_CLIENT_EMAIL + "CLIENT_EMAIL "
            + PREFIX_CLIENT_ASKING_PRICE + "CLIENT_ASKING_PRICE \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Mayfair "
            + PREFIX_TYPE + "Condo "
            + PREFIX_ADDRESS + "1 Jurong East Street 32 "
            + PREFIX_POSTAL + "609477 "
            + PREFIX_DEADLINE + "31-12-2021 "
            + PREFIX_REMARK + "Urgent to sell "
            + PREFIX_CLIENT_NAME + "Alice "
            + PREFIX_CLIENT_CONTACT + "91234567 "
            + PREFIX_CLIENT_EMAIL + "alice@gmail.com "
            + PREFIX_CLIENT_ASKING_PRICE + "$800,000";

    public static final String MESSAGE_SUCCESS = "Edited property: %1$s";
    public static final String MESSAGE_DUPLICATE_PROPERTY = "This property already exists in the app";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditPropertyDescriptor editPropertyDescriptor;

    /**
     * Creates an AddPropertyCommand to add the specified {@code Property}.
     */
    public EditPropertyCommand(Index index, EditPropertyDescriptor editPropertyDescriptor) {
        requireNonNull(index);
        requireNonNull(editPropertyDescriptor);

        this.index = index;
        this.editPropertyDescriptor = new EditPropertyCommand.EditPropertyDescriptor(editPropertyDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getPropertySize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Property propertyToEdit = model.getProperty(index.getZeroBased());
        Property editedProperty = createEditedProperty(propertyToEdit, editPropertyDescriptor);

        if (!propertyToEdit.isSameProperty(editedProperty) && model.hasProperty(editedProperty)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROPERTY);
        }

        model.setProperty(index.getZeroBased(), editedProperty);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedProperty));
    }

    /**
     * Creates and returns a {@code Property} with the details of {@code propertyToEdit}
     * edited with {@code editPropertyDescriptor}.
     */
    private static Property createEditedProperty(Property propertyToEdit,
                                               EditPropertyCommand.EditPropertyDescriptor editPropertyDescriptor) {
        assert propertyToEdit != null;

        Name updatedName = editPropertyDescriptor.getName().orElse(propertyToEdit.getName());
        Type updatedType = editPropertyDescriptor.getType().orElse(propertyToEdit.getPropertyType());
        Address updatedAddress = editPropertyDescriptor.getAddress().orElse(propertyToEdit.getAddress());
        PostalCode updatedPostalCode = editPropertyDescriptor.getPostalCode().orElse(propertyToEdit.getPostalCode());
        Deadline updatedDeadline = editPropertyDescriptor.getDeadline().orElse(propertyToEdit.getDeadline());
        Remark updatedRemark = editPropertyDescriptor.getRemark().orElse(propertyToEdit.getRemarks());

        Client updatedClient = createEditedClient(propertyToEdit.getClient(), editPropertyDescriptor.getClientDescriptor());

        return new Property(updatedName, updatedType, updatedAddress, updatedPostalCode, updatedDeadline,
                updatedRemark, updatedClient);
    }

    /**
     * Creates and returns a {@code Client} with the details of {@code clientToEdit}
     * edited with {@code editClientDescriptor}.
     */
    private static Client createEditedClient(Client clientToEdit,
                                                 Optional<EditPropertyCommand.EditClientDescriptor> clientDescriptor) {

        if (clientDescriptor.isPresent()) {
            EditClientDescriptor editClientDescriptor = clientDescriptor.get();

            Name updatedName = editClientDescriptor.getName().orElse(clientToEdit.getClientName());
            Contact updatedContact =
                    editClientDescriptor.getContact().orElse(clientToEdit.getClientContact());
            Email updatedEmail =
                    editClientDescriptor.getEmail().orElse(clientToEdit.getClientEmail());
            AskingPrice updatedAskingPrice =
                    editClientDescriptor.getAskingPrice().orElse(clientToEdit.getClientAskingPrice());

            return new Client(updatedName, updatedContact, updatedEmail, updatedAskingPrice);
        } else {
            return clientToEdit;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditPropertyCommand // instanceof handles nulls
                && editPropertyDescriptor.equals(((EditPropertyCommand) other).editPropertyDescriptor));
    }

    public static class EditPropertyDescriptor {
        private Name name;
        private Type type;
        private Address address;
        private PostalCode postalCode;
        private Deadline deadline;
        private Remark remark;
        private EditClientDescriptor editClientDescriptor;

        public EditPropertyDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPropertyDescriptor(EditPropertyCommand.EditPropertyDescriptor toCopy) {
            setName(toCopy.name);
            setType(toCopy.type);
            setAddress(toCopy.address);
            setPostalCode(toCopy.postalCode);
            setDeadline(toCopy.deadline);
            setRemark(toCopy.remark);
            setClientDescriptor(toCopy.editClientDescriptor);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, type, address, postalCode, deadline, remark, editClientDescriptor);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Optional<Type> getType() {
            return Optional.ofNullable(type);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setPostalCode(PostalCode postalCode) {
            this.postalCode = postalCode;
        }

        public Optional<PostalCode> getPostalCode() {
            return Optional.ofNullable(postalCode);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setClientDescriptor(EditClientDescriptor editClientDescriptor) {
            this.editClientDescriptor = editClientDescriptor;
        }

        public Optional<EditClientDescriptor> getClientDescriptor() {
            return Optional.ofNullable(editClientDescriptor);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPropertyCommand.EditPropertyDescriptor)) {
                return false;
            }

            // state check
            EditPropertyCommand.EditPropertyDescriptor e = (EditPropertyCommand.EditPropertyDescriptor) other;

            return getName().equals(e.getName())
                    && getType().equals(e.getType())
                    && getAddress().equals(e.getAddress())
                    && getPostalCode().equals(e.getPostalCode())
                    && getDeadline().equals(e.getDeadline())
                    && getRemark().equals(e.getRemark())
                    && getClientDescriptor().equals(e.getClientDescriptor());
        }
    }

    public static class EditClientDescriptor {
        private Name name;
        private Contact contact;
        private Email email;
        private AskingPrice askingPrice;

        public EditClientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditClientDescriptor(EditPropertyCommand.EditClientDescriptor toCopy) {
            setName(toCopy.name);
            setContact(contact);
            setEmail(email);
            setAskingPrice(askingPrice);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, contact, email, askingPrice);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public Optional<Contact> getContact() {
            return Optional.ofNullable(contact);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAskingPrice(AskingPrice askingPrice) {
            this.askingPrice = askingPrice;
        }

        public Optional<AskingPrice> getAskingPrice() {
            return Optional.ofNullable(askingPrice);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPropertyCommand.EditPropertyDescriptor)) {
                return false;
            }

            // state check
            EditPropertyCommand.EditClientDescriptor e = (EditPropertyCommand.EditClientDescriptor) other;

            return getName().equals(e.getName())
                    && getContact().equals(e.getContact())
                    && getEmail().equals(e.getEmail())
                    && getAskingPrice().equals(e.getAskingPrice());
        }
    }
}
