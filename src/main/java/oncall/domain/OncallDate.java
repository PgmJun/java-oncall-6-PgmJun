package oncall.domain;

import oncall.view.dto.OncallDateDto;

public class OncallDate {
    private final int month;
    private final String dayOfWeek;

    private final static int MIN_MONTH = 1;
    private final static int MAX_MONTH = 12;
    private final static int FRIDAY_INDEX = 4;
    private final static int WEEK_SIZE = 7;


    public OncallDate(OncallDateDto dto) {
        validate(dto);
        this.month = dto.getMonth();
        this.dayOfWeek = dto.getDayOfWeek();
    }

    private void validate(OncallDateDto dto) {
        validateMonth(dto);
        validateDayOfWeek(dto);
    }

    private static void validateDayOfWeek(OncallDateDto dto) {
        if (!DayOfWeek.isPresent(dto.getDayOfWeek())) {
            throw new IllegalArgumentException("요일은 월,화,수,목,금,토,일 중 하나만 입력 가능합니다.");
        }
    }

    private static void validateMonth(OncallDateDto dto) {
        if (dto.getMonth() < MIN_MONTH || dto.getMonth() > MAX_MONTH) {
            throw new IllegalArgumentException("월은 1~12월만 입력할 수 있습니다.");
        }
    }

    public int getMonth() {
        return month;
    }

    public int dateOfMonth() {
        return Month.getNumberByMonth(month);
    }

    public boolean isHolidayOrWeekend(int month, int date) {
        int dayOfWeekIndex = calculateDayOfWeekIndex(date);
        if (isHoliday(month, date) || (dayOfWeekIndex > FRIDAY_INDEX)) {
            return true;
        }
        return false;
    }

    public boolean isHoliday(int month, int date) {
        return Month.isHoliday(month, date);
    }

    public int calculateDayOfWeekIndex(int date) {
        return (DayOfWeek.findCodeByName(dayOfWeek) + (date - 1)) % WEEK_SIZE;
    }
}
