package bankModule;

import gLoan.Date;

public class DateCast<type extends Number> extends Date {
    public DateCast(String year, String month, String day) {
        super(Short.valueOf(year), Short.valueOf(month), Short.valueOf(day));
    }

    public DateCast(type year, type month, type day) {
        super((short) year.intValue(), (short) month.intValue(), (short) day.intValue());
    }
}

