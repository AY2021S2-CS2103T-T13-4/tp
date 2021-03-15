package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void testStringConversion() {
        assertEquals("9:00am",
                new Time(LocalTime.parse("09:00")).toString().toLowerCase());
        assertEquals("11:59pm",
                new Time(LocalTime.parse("23:59")).toString().toLowerCase());
    }
}
