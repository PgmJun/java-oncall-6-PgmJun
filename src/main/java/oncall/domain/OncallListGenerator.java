package oncall.domain;

import java.util.List;
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

        OncallWorkers oncallWorkers = inputOncallWorkers();
    }

    private OncallWorkers inputOncallWorkers() {
        while (true) {
            try {
                Workers weekdayWorkers = new Workers(inputWeekdayWorkerInfos());
                Workers weekendWorkers = new Workers(inputWeekendWorkerInfos());
                return new OncallWorkers(weekdayWorkers, weekendWorkers);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private List<Worker> inputWeekdayWorkerInfos() {
        return inputView.readWeekdayWorkers().stream()
                .map(Worker::new)
                .toList();
    }

    private List<Worker> inputWeekendWorkerInfos() {
        return inputView.readWeekendWorkers().stream()
                .map(Worker::new)
                .toList();
    }

    private OncallDate inputOncallDate() {
        while (true) {
            try {
                OncallDateDto oncallDateDto = inputView.readOncallDate();
                return new OncallDate(oncallDateDto);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
