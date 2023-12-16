package oncall.domain;

import java.util.Collections;
import java.util.List;

public class Workers {
    private final List<Worker> workers;

    public Workers(List<Worker> workers) {
        validate(workers);
        this.workers = workers;
    }

    private void validate(List<Worker> workers) {
        validateWorkerNameDuplication(workers);
        validateWorkersSize(workers);
    }

    private static void validateWorkersSize(List<Worker> workers) {
        if(workers.size() < 5 || workers.size() > 35) {
            throw new IllegalArgumentException("근무자는 5명 이상 35명 이하만 가능합니다.");
        }
    }

    private void validateWorkerNameDuplication(List<Worker> workers) {
        long distinctWorkersNameCount = workers.stream()
                .map(Worker::getName)
                .distinct()
                .count();
        if(distinctWorkersNameCount != workers.size()) {
            throw new IllegalArgumentException("근무자의 이름은 중복될 수 없습니다.");
        }
    }

    public List<Worker> getWorkers() {
        return Collections.unmodifiableList(workers);
    }

    public Worker getWorkerByIndex(int index) {
        return workers.get(index);
    }
}
