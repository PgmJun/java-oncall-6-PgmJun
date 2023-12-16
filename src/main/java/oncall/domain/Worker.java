package oncall.domain;

public class Worker {
    private final String name;

    public Worker(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if(name.isBlank() || name.length() < 1 || name.length() > 5) {
            throw new IllegalArgumentException("근무자명은 1자 이상 5자 이하여야 합니다");
        }
    }

    public String getName() {
        return name;
    }
}
