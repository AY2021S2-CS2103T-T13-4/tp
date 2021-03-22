package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyContainsKeywordsPredicate;
import seedu.address.model.property.PropertyPredicateList;
import seedu.address.model.property.PropertyPricePredicate;
import seedu.address.model.property.PropertyTypePredicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_LESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_PRICE_MORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TAG;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;
import static seedu.address.testutil.TypicalProperties.getTypicalProperties;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindAppointmentCommand}.
 */
public class FindPropertyCommandTest {
    private Model model = new ModelManager(getTypicalPropertyBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void equalsKeywords() {
        PropertyContainsKeywordsPredicate firstPredicate =
                new PropertyContainsKeywordsPredicate(Collections.singletonList("first"));
        PropertyContainsKeywordsPredicate secondPredicate =
                new PropertyContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPropertyCommand findFirstCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        FindPropertyCommand findSecondCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(secondPredicate)));

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy =
                new FindPropertyCommand(new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different values -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void equalsPrice() {
        PropertyPricePredicate firstPredicate =
                new PropertyPricePredicate("10000", true);
        PropertyPricePredicate secondPredicate =
                new PropertyPricePredicate("2000", false);

        FindPropertyCommand findFirstCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        FindPropertyCommand findSecondCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(secondPredicate)));

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy =
                new FindPropertyCommand(new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different values -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void equalsType() {
        PropertyTypePredicate firstPredicate =
                new PropertyTypePredicate("HDB");
        PropertyTypePredicate secondPredicate =
                new PropertyTypePredicate("condo");

        FindPropertyCommand findFirstCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        FindPropertyCommand findSecondCommand =
                new FindPropertyCommand(
                        new PropertyPredicateList(Collections.singletonList(secondPredicate)));

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy =
                new FindPropertyCommand(new PropertyPredicateList(Collections.singletonList(firstPredicate)));
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different values -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void noKeywordsTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 0);
        PropertyPredicateList predicate = preparePredicate(" ");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());
    }

    @Test
    public void oneKeywordTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW_SINGULAR, 1);
        PropertyPredicateList predicate = preparePredicate("Mayfair");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(MAYFAIR), model.getFilteredPropertyList());
    }

    @Test
    public void multiKeywordsTest() {
        String expectedMessage = String.format(MESSAGE_PROPERTIES_LISTED_OVERVIEW, 3);
        PropertyPredicateList predicate = preparePredicate("Mayfair Woodlands Burghley");
        FindPropertyCommand command = new FindPropertyCommand(predicate);
        expectedModel.updateFilteredPropertyList(predicate.combine());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(getTypicalProperties(), model.getFilteredPropertyList());
    }

    /**
     * Parses {@code userInput} into a {@code Predicate}.
     */
    private PropertyPredicateList preparePredicate(String userInput) {
        String[] nameKeywords = userInput.trim().split("\\s+");

        List<Predicate<Property>> predicates = new ArrayList<>();
        List<String> keywords = new ArrayList<>();

        for (String s : nameKeywords) {
            if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_MORE))) {
                predicates.add(new PropertyPricePredicate(s.split("/")[1], false));
            } else if (s.startsWith(String.valueOf(PREFIX_PROPERTY_PRICE_LESS))) {
                predicates.add(new PropertyPricePredicate(s.split("/")[1], true));
            } else if (s.startsWith(String.valueOf(PREFIX_PROPERTY_TAG))) {
                predicates.add(new PropertyTypePredicate(s.split("/")[1]));
            } else {
                keywords.add(s);
            }
        }

        if (keywords.size() > 0) {
            predicates.add(new PropertyContainsKeywordsPredicate(keywords));
        }

        return new PropertyPredicateList(predicates);

    }
}
