package oncall.domain;

import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.dto.OncallDateDto;

public class OncallListGenerator {
    private final InputView inputView;
    private final OutputView outputView;

    public OncallListGenerator(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        OncallDate oncallDate = inputOncallDate();
    }

    private OncallDate inputOncallDate() {
        while(true) {
            try {
                OncallDateDto oncallDateDto = inputView.readOncallDate();
                return new OncallDate(oncallDateDto);
            }catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
