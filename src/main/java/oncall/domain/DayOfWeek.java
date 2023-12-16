package oncall.domain;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY("월", 0),
    TUESDAY("화", 1),
    WEDNESDAY("수", 2),
    THURSDAY("목", 3),
    FRIDAY("금", 4),
    SATURDAY("토", 5),
    SUNDAY("일", 6);

    private final String name;
    private final int code;

    DayOfWeek(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static String getNameByCode(int code) {
        return Arrays.stream(values())
                .filter(d -> d.code == code)
                .findFirst()
                .get()
                .name;
    }

    public static boolean isPresent(String name) {
        return Arrays.stream(values())
                .anyMatch(d -> d.name.equals(name));
    }

    public static int findCodeByName(String name) {
        return Arrays.stream(values())
                .filter(d -> d.name.equals(name))
                .findFirst()
                .get()
                .code;
    }
}
