package oncall.domain;

import java.util.List;
import java.util.stream.Collectors;
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
        OncallInfo oncallInfo = OncallInfo.ofDateAndWorkers(oncallDate, oncallWorkers);

        List<Worker> workSequence = oncallInfo.getWorkSequence();
        outputView.printOncallList(workSequence, oncallDate);
    }

    private OncallWorkers inputOncallWorkers() {
        while (true) {
            try {
                Workers weekdayWorkers = Workers.from(inputWeekdayWorkerInfos());
                Workers weekendWorkers = Workers.from(inputWeekendWorkerInfos());
                return new OncallWorkers(weekdayWorkers, weekendWorkers);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }

    private List<Worker> inputWeekdayWorkerInfos() {
        return inputView.readWeekdayWorkers().stream()
                .map(Worker::new)
                .collect(Collectors.toList());
    }

    private List<Worker> inputWeekendWorkerInfos() {
        return inputView.readWeekendWorkers().stream()
                .map(Worker::new)
                .collect(Collectors.toList());
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
