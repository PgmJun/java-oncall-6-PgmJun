package oncall.view;

public class OutputView {
    private final static String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(Exception exception) {
        System.out.println(ERROR_PREFIX + exception.getMessage());
    }
}
