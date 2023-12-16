package oncall.view.validator;

import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern DATE_FORMAT = Pattern.compile("^(\\d+),([가-힣])$");

    public void validateDateFormat(String value) {
        if (!DATE_FORMAT.matcher(value).matches()) {
            throw new IllegalArgumentException("월,요일 형태로 입력해야 합니다.");
        }
    }
}
