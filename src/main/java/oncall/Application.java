package oncall;

import oncall.domain.OncallListGenerator;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.validator.InputValidator;

public class Application {
    public static void main(String[] args) {
        OncallListGenerator oncallListGenerator = new OncallListGenerator(new InputView(new InputValidator()),
                new OutputView());
        oncallListGenerator.start();
    }
}
