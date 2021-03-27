package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PropertyBook;
import seedu.address.testutil.TypicalModelManager;

public class ClearAllCommandTest {

    @Test
    public void execute_emptyAppointmentBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyAppointmentBook_success() {
        Model model = TypicalModelManager.getTypicalModelManager();
        Model expectedModel = TypicalModelManager.getTypicalModelManager();
        expectedModel.setAppointmentBook(new AppointmentBook());

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_emptyPropertyBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_nonEmptyPropertyBook_success() {
        Model model = TypicalModelManager.getTypicalModelManager();
        Model expectedModel = TypicalModelManager.getTypicalModelManager();
        expectedModel.setPropertyBook(new PropertyBook());

        assertCommandSuccess(new ClearAllCommand(), model, ClearAllCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

}
