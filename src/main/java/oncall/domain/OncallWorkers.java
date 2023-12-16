package oncall.domain;

public class OncallWorkers {
    private final Workers weekdayWorkers;
    private final Workers weekendWorkers;

    public OncallWorkers(Workers weekdayWorkers, Workers weekendWorkers) {
        validate(weekdayWorkers, weekendWorkers);
        this.weekdayWorkers = weekdayWorkers;
        this.weekendWorkers = weekendWorkers;
    }

    private void validate(Workers weekdayWorkers, Workers weekendWorkers) {
        validateWorkersSize(weekdayWorkers, weekendWorkers);
        validateIsWorkerWorkOneDay(weekdayWorkers, weekendWorkers);
    }

    private void validateIsWorkerWorkOneDay(Workers weekdayWorkers, Workers weekendWorkers) {
        for(int i = 0; i < weekdayWorkers.getWorkers().size(); i++) {
            String weekdayWorkerName = weekdayWorkers.getWorkerByIndex(i).getName();
            weekdayWorkers.getWorkers().stream()
                    .filter(worker -> worker.getName().equals(weekdayWorkerName))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("근무자는 평일, 휴일 모두에 한 번씩 포함되어야 합니다."));
        }

    }

    private void validateWorkersSize(Workers weekdayWorkers, Workers weekendWorkers) {
        if(weekdayWorkers.getWorkers().size() != weekendWorkers.getWorkers().size()) {
            throw new IllegalArgumentException("평일 근무자와 휴일 근무자의 수가 다릅니다.");
        }
    }

    public Workers getWeekdayWorkers() {
        return weekdayWorkers;
    }

    public Workers getWeekendWorkers() {
        return weekendWorkers;
    }
}
