package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Property's type.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}
 */
public class Type {
    private static final String MESSAGE_CONSTRAINTS =
            "Property types should only be either hdb, condo or landed";

    /*
     * The property types should only be one of three types: hdb, condo, or landed.
     */
    private static final String VALIDATION_REGEX = "^(hdb|condo|landed)$";

    private final String propertyType;

    Type(String propertyType) {
        requireNonNull(propertyType);
        checkArgument(isValidType(propertyType), MESSAGE_CONSTRAINTS);
        this.propertyType = propertyType;
    }

    /**
     * Returns true if a given string is a valid property type.
     * @param test The string to test.
     * @return true If the given string is a valid property type, otherwise false.
     */
    public boolean isValidType(String test) {
        String lowercaseTest = test.toLowerCase();
        return lowercaseTest.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return propertyType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Type)) {
            return false;
        }
        Type otherType = (Type) other;
        return propertyType.equals(otherType.propertyType);
    }

    @Override
    public int hashCode() {
        return propertyType.hashCode();
    }
}
