package oncall.domain;

import java.util.List;
import java.util.Optional;
import oncall.view.dto.OncallDateDto;

public class OncallDate {
    private final int month;
    private final String dayOfWeek;

    private final static List<String> dayOfWeeks = List.of("월", "화", "수", "목", "금", "토", "일");
    private final static List<Integer> dates = List.of(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    private final static List<List<Integer>> holyDays = List.of(List.of(), List.of(1), List.of(), List.of(1), List.of(),
            List.of(5), List.of(6), List.of(), List.of(15), List.of(), List.of(3, 9), List.of(), List.of(25));

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
        Optional<String> matchedMonth = dayOfWeeks.stream()
                .filter(month -> month.equals(dto.getDayOfWeek()))
                .findFirst();

        if (matchedMonth.isEmpty()) {
            throw new IllegalArgumentException("요일은 월,화,수,목,금,토,일 중 하나만 입력 가능합니다.");
        }
    }

    private static void validateMonth(OncallDateDto dto) {
        if (dto.getMonth() < 1 || dto.getMonth() > 12) {
            throw new IllegalArgumentException("월은 1~12월만 입력할 수 있습니다.");
        }
    }

    public int getMonth() {
        return month;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int dateOfMonth() {
        return dates.get(month);
    }

    public int getDayOfWeekIndex() {
        return dayOfWeeks.indexOf(dayOfWeek);
    }

    public boolean isHolyDay(int month, int date) {
        int dayOfWeekIndex = calculateDayOfWeekIndex(date);
        if (holyDays.get(month).contains(date) || (dayOfWeekIndex > 4)) {
            return true;
        }
        return false;
    }

    public int calculateDayOfWeekIndex(int date) {
        return (dayOfWeeks.indexOf(dayOfWeek) + (date - 1)) % 7;
    }
}
