package oncall.domain;

import java.util.List;
import java.util.Optional;
import oncall.view.dto.OncallDateDto;

public class OncallDate {
    private final int month;
    private final String dayOfWeek;

    private final static List<String> months = List.of("월", "화", "수", "목", "금", "토", "일");

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
        Optional<String> matchedMonth = months.stream()
                .filter(month -> month.equals(dto.getDayOfWeek()))
                .findFirst();

        if(matchedMonth.isEmpty()) {
            throw new IllegalArgumentException("요일은 월,화,수,목,금,토,일 중 하나만 입력 가능합니다.");
        }
    }

    private static void validateMonth(OncallDateDto dto) {
        if(dto.getMonth() < 1 || dto.getMonth() > 12) {
            throw new IllegalArgumentException("월은 1~12월만 입력할 수 있습니다.");
        }
    }
}
