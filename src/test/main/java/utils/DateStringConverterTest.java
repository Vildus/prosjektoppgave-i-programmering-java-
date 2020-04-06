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
        // denne testen i seg selv hadde vært "verdiløs" uten den testen over
        // fordi toString kunne ha returnert en tom streng, som ikke er gyldig
        // med siden vi matcher på dateStr1 == dateStr2 så ville de vært like tomme
        Date date1 = new Date();
        String dateStr1 = this.dateStringConverter.toString(date1);
        //Resultatet av dato1 når den har vært en streng og blir en dato igjen skal være lik dato2 som er en dato

        Date date2 = this.dateStringConverter.fromString(dateStr1);

        String dateStr2 = this.dateStringConverter.toString(date2);

        assertEquals(dateStr1, dateStr2);
    }
}