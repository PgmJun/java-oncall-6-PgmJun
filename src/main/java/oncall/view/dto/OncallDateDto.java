package oncall.view.dto;

public class OncallDateDto {
    private final int month;
    private final String dayOfWeek;

    public OncallDateDto(int month, String dayOfWeek) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}
