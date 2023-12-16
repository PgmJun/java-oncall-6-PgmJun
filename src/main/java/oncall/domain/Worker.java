package oncall.domain;

public class Worker {
    private final String name;

    private final static int MIN_NAME_LENGTH = 1;
    private final static int MAX_NAME_LENGTH = 5;

    public Worker(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.isBlank() || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("근무자명은 1자 이상 5자 이하여야 합니다");
        }
    }

    public String getName() {
        return name;
    }
}
