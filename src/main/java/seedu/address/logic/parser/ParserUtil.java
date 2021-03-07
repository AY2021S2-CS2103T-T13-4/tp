package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.Name;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Remark;
import seedu.address.model.property.Type;
import seedu.address.model.util.DateFormat;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name The property name string to be parsed.
     * @return A {@code Name}.
     * @throws ParseException If the given {@code name} is invalid.
     */
    public static Name parsePropertyName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String type} into a {@code Type}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param type The property type string to be parsed.
     * @return A {@code Type}.
     * @throws ParseException If the given {@code type} is invalid.
     */
    public static Type parsePropertyType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!Type.isValidType(trimmedType)) {
            throw new ParseException(Type.MESSAGE_CONSTRAINTS);
        }
        return new Type(trimmedType);
    }

    /**
     * Parses an {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param address The property address string to be parsed.
     * @return An {@code Address}.
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static Address parsePropertyAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String postal} into a {@code Postal}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param postal The property postal string to be parsed.
     * @return A {@code Postal}.
     * @throws ParseException If the given {@code postal} is invalid.
     */
    public static PostalCode parsePropertyPostal(String postal) throws ParseException {
        requireNonNull(postal);
        String trimmedPostal = postal.trim();
        if (!PostalCode.isValidPostal(trimmedPostal)) {
            throw new ParseException(PostalCode.MESSAGE_CONSTRAINTS);
        }
        return new PostalCode(trimmedPostal);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param deadline The property deadline string to be parsed.
     * @return A {@code Deadline}.
     * @throws ParseException If the given {@code deadline} is invalid.
     */
    public static Deadline parsePropertyDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        try {
            return new Deadline(LocalDate.parse(trimmedDeadline, DateFormat.INPUT_DATE_FORMAT));
        } catch (DateTimeParseException ex) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param remark The property remark string to be parsed.
     * @return A {@code Remark}.
     * @throws ParseException If the given {@code remark} is invalid.
     */
    public static Remark parsePropertyRemark(String remark) throws ParseException {
        if (remark == null) {
            return null;
        }
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    // ======================================================================================================
    // Placeholders for Person object below to handle errors

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.address.model.person.Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!seedu.address.model.person.Name.isValidName(trimmedName)) {
            throw new ParseException(seedu.address.model.person.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.person.Name(trimmedName);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static seedu.address.model.person.Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!seedu.address.model.person.Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(seedu.address.model.person.Address.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.person.Address(trimmedAddress);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
