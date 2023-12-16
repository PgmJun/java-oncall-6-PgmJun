package oncall.domain;

import java.util.Arrays;
import java.util.List;

public enum Month {
    JANUARY(1, 31, List.of(1)),
    FEBRUARY(2, 28, List.of()),
    MARCH(3, 31, List.of(1)),
    APRIL(4, 30, List.of()),
    MAY(5, 31, List.of(5)),
    JUNE(6, 30, List.of(6)),
    JULY(7, 31, List.of()),
    AUGUST(8, 31, List.of(15)),
    SEPTEMBER(9, 30, List.of()),
    OCTOBER(10, 31, List.of(3, 9)),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(25));

    private final int month;
    private final int days;
    private final List<Integer> holidays;

    Month(int month, int days, List<Integer> holidays) {
        this.month = month;
        this.days = days;
        this.holidays = holidays;
    }

    public static int getNumberByMonth(int month) {
        return Arrays.stream(values())
                .filter(m -> m.month == month)
                .findAny()
                .get().days;
    }

    public static boolean isHoliday(int month, int date) {
        return Arrays.stream(values())
                .filter(m -> m.month == month)
                .findAny()
                .get()
                .holidays.contains(date);
    }
}
