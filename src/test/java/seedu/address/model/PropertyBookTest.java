package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_MAYFAIR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.testutil.PropertyBuilder;

public class PropertyBookTest {

    private final PropertyBook propertyBook = new PropertyBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), propertyBook.getPropertyList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPropertyBook_replacesData() {
        PropertyBook newData = getTypicalPropertyBook();
        propertyBook.resetData(newData);
        assertEquals(newData, propertyBook);
    }

    @Test
    public void resetData_withDuplicateProperties_throwsDuplicatePropertyException() {
        // Two Properties with the same identity fields
        Property editedWoodlandsCrescent = new PropertyBuilder(WOODLANDS_CRESCENT)
                .withAddress(VALID_ADDRESS_MAYFAIR).withType(VALID_TYPE_MAYFAIR)
                .withPostal(VALID_POSTAL_MAYFAIR).withDeadline(LocalDate.parse(VALID_DEADLINE_MAYFAIR))
                .build();
        List<Property> newProperties = Arrays.asList(WOODLANDS_CRESCENT, editedWoodlandsCrescent);
        PropertyBookStub newData = new PropertyBookStub(newProperties);

        assertThrows(DuplicatePropertyException.class, () -> propertyBook.resetData(newData));
    }

    @Test
    public void hasProperty_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> propertyBook.hasProperty(null));
    }

    @Test
    public void hasProperty_propertyNotInPropertyBook_returnsFalse() {
        assertFalse(propertyBook.hasProperty(WOODLANDS_CRESCENT));
    }

    @Test
    public void hasProperty_propertyInPropertyBook_returnsTrue() {
        propertyBook.addProperty(WOODLANDS_CRESCENT);
        assertTrue(propertyBook.hasProperty(WOODLANDS_CRESCENT));
    }

    @Test
    public void hasProperty_propertyWithSameIdentityFieldsInPropertyBook_returnsTrue() {
        propertyBook.addProperty(WOODLANDS_CRESCENT);
        Property editedWoodlandsCrescent = new PropertyBuilder(WOODLANDS_CRESCENT)
                .withAddress(VALID_ADDRESS_MAYFAIR).withType(VALID_TYPE_MAYFAIR)
                .withPostal(VALID_POSTAL_MAYFAIR).withDeadline(LocalDate.parse(VALID_DEADLINE_MAYFAIR))
                .build();
        assertTrue(propertyBook.hasProperty(editedWoodlandsCrescent));
    }

    @Test
    public void getPropertyList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> propertyBook.getPropertyList().remove(0));
    }

    /**
     * A stub ReadOnlyPropertyBook whose Properties list can violate interface constraints.
     */
    private static class PropertyBookStub implements ReadOnlyPropertyBook {
        private final ObservableList<Property> properties = FXCollections.observableArrayList();

        PropertyBookStub(Collection<Property> properties) {
            this.properties.setAll(properties);
        }

        @Override
        public ObservableList<Property> getPropertyList() {
            return properties;
        }
    }

}


