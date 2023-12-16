package oncall.domain;

import java.util.List;

public class Workers {
    private List<Worker> workers;

    public Workers(List<Worker> workers) {
        this.workers = workers;
    }

    public static Workers from(List<Worker> workers) {
        validate(workers);
        return new Workers(workers);
    }

    private static void validate(List<Worker> workers) {
        validateWorkerNameDuplication(workers);
        validateWorkersSize(workers);
    }

    private static void validateWorkersSize(List<Worker> workers) {
        if (workers.size() < 5 || workers.size() > 35) {
            throw new IllegalArgumentException("근무자는 5명 이상 35명 이하만 가능합니다.");
        }
    }

    private static void validateWorkerNameDuplication(List<Worker> workers) {
        long distinctWorkersNameCount = workers.stream()
                .map(Worker::getName)
                .distinct()
                .count();
        if (distinctWorkersNameCount != workers.size()) {
            throw new IllegalArgumentException("근무자의 이름은 중복될 수 없습니다.");
        }
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public Worker getWorkerByIndex(int index) {
        return workers.get(index);
    }

    public void changeSequence(int i1, int i2) {
        Worker worker1 = new Worker(workers.get(i1).getName());
        Worker worker2 = new Worker(workers.get(i2).getName());
        workers.set(i1, worker2);
        workers.set(i2, worker1);
    }
}
