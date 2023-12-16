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

    public Stack<Worker> getWorkSequence() {
        Stack<Worker> workers = new Stack<>();
        int weekendWorkerIndex = 0;
        int weekdayWorkerIndex = 0;
        //첫 근무자 정보 입력
        if (oncallDate.isHolyDayOrWeekend(oncallDate.getMonth(), 1)) {
            workers.push(weekendWorkers.getWorkerByIndex(weekendWorkerIndex++));
        } else if (!oncallDate.isHolyDayOrWeekend(oncallDate.getMonth(), 1)) {
            workers.push(weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex++));
        }

        // 근무자 정보 입력
        int month = oncallDate.getMonth();
        for (int date = 2; date <= oncallDate.dateOfMonth(); date++) {
            if (weekdayWorkerIndex % weekdayWorkers.getWorkers().size() == 0) {
                weekdayWorkerIndex = 0;
            }
            if (weekendWorkerIndex % weekendWorkers.getWorkers().size() == 0) {
                weekendWorkerIndex = 0;
            }
            //주말의 경우
            if (oncallDate.isHolyDayOrWeekend(month, date)) {
                //주말 현재 순서의 근무자 정보 조회
                Worker todayWorker = weekendWorkers.getWorkerByIndex(weekendWorkerIndex);
                //전날 근무자가 본인이면
                if (workers.peek().getName().equals(todayWorker.getName())) {
                    //다음날 주말 근무자와 순서 변경
                    changeWeekendWorkerSequence(weekendWorkerIndex);
                }
                workers.push(weekendWorkers.getWorkerByIndex(weekendWorkerIndex));
                //근무 순서 +1
                weekendWorkerIndex += 1;
                continue;
            }
            //평일의 경우
            Worker todayWorker = weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex);
            //전날 근무자가 본인이면
            if (workers.peek().getName().equals(todayWorker.getName())) {
                //다음날 평일 근무자와 순서 변경
                changeWeekdayWorkerSequence(weekdayWorkerIndex);
            }
            workers.push(weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex));
            weekdayWorkerIndex += 1;
        }

        return workers;
    }

    private void changeWeekdayWorkerSequence(int weekdayWorkerIndex) {
        weekdayWorkers.changeSequence(weekdayWorkerIndex, weekdayWorkerIndex + 1);
    }

    private void changeWeekendWorkerSequence(int weekendWorkerIndex) {
        weekendWorkers.changeSequence(weekendWorkerIndex, weekendWorkerIndex + 1);
    }

    private boolean isWorkContinuous(List<Worker> workers, Worker todayWorker) {
        return workers.get(workers.size() - 1).getName().equals(todayWorker.getName());

    }
}
