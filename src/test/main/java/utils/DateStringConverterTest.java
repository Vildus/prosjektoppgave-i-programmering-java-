package utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateStringConverterTest {

    private DateStringConverter dateStringConverter = new DateStringConverter("dd/MM/yyyy");

    @Test
    void testToString() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(2020, 0, 15);
        Date date1 = cal.getTime();

        String dateStr1 = this.dateStringConverter.toString(date1);

        assertEquals(dateStr1, "15/01/2020");
    }

    @Test
    void testFromToString() throws ParseException {
        Date date1 = new Date();
        String dateStr1 = this.dateStringConverter.toString(date1);

        Date date2 = this.dateStringConverter.fromString(dateStr1);

        String dateStr2 = this.dateStringConverter.toString(date2);

        assertEquals(dateStr1, dateStr2);
    }
}