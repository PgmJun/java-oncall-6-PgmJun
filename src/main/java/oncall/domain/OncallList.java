package oncall.domain;

import java.util.List;
import java.util.Stack;

public class OncallList {
    private final OncallDate oncallDate;
    private final Workers weekdayWorkers;
    private final Workers weekendWorkers;

    public OncallList(OncallDate oncallDate, OncallWorkers oncallWorkers) {
        this.oncallDate = oncallDate;
        this.weekdayWorkers = oncallWorkers.getWeekdayWorkers();
        this.weekendWorkers = oncallWorkers.getWeekendWorkers();
    }

    public List<Worker> getWorkSequence() {
        List<Worker> workers = new Stack<>();
        int month = oncallDate.getMonth();
        int weekendWorkerIndex = 0;
        int weekdayWorkerIndex = 0;
        for (int date = 1; date <= oncallDate.dateOfMonth(); date++) {
            Worker todayWorker = null;
            if(weekendWorkerIndex % weekendWorkers.getWorkers().size() == 0) {
                weekendWorkerIndex = 0;
            }
            if(weekdayWorkerIndex % weekdayWorkers.getWorkers().size() == 0) {
                weekdayWorkerIndex = 0;
            }
            boolean isHolyDay = oncallDate.isHolyDay(month, date);

            if (isHolyDay) {
                todayWorker = weekendWorkers.getWorkerByIndex(weekendWorkerIndex++);
            } else if(!isHolyDay) {
                todayWorker = weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex++);
            }

            if (workers.isEmpty()) {
                workers.add(todayWorker);
                continue;
            }
            checkWorkContinuous(workers, todayWorker, isHolyDay, weekendWorkerIndex, weekdayWorkerIndex);
            if (isHolyDay) {
                todayWorker = weekendWorkers.getWorkerByIndex(weekendWorkerIndex - 1);
                workers.add(todayWorker);
                continue;
            }
            todayWorker = weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex - 1);
            workers.add(todayWorker);
        }

        return workers;
    }

    private void checkWorkContinuous(List<Worker> workers, Worker todayWorker, boolean isHolyDay,
                                     int weekendWorkerIndex,
                                     int weekdayWorkerIndex) {
        if (workers.get(workers.size() - 1).getName().equals(todayWorker.getName())) {
            if (isHolyDay) {
                weekendWorkers.changeSequence(weekendWorkerIndex - 1, weekendWorkerIndex);
                return;
            }
            weekdayWorkers.changeSequence(weekdayWorkerIndex - 1, weekdayWorkerIndex);
        }
    }
}
