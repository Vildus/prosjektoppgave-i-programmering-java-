package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringConverter {

    private DateFormat dateFormat;

    public DateStringConverter(String format) {
        this.dateFormat = new SimpleDateFormat(format);
    }

    public Date fromString(String s) throws ParseException {
        return this.dateFormat.parse(s);
    }

    public String toString(Date date) {
        return dateFormat.format(date);
    }
}