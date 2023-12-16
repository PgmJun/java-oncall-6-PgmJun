package oncall.domain;

import java.util.Stack;

public class OncallList {
    private final OncallDate oncallDate;
    private final Workers weekdayWorkers;
    private final Workers weekendWorkers;
    private int weekendWorkerIndex;
    private int weekdayWorkerIndex;

    public OncallList(OncallDate oncallDate, OncallWorkers oncallWorkers) {
        this.oncallDate = oncallDate;
        this.weekdayWorkers = oncallWorkers.getWeekdayWorkers();
        this.weekendWorkers = oncallWorkers.getWeekendWorkers();
        resetWorkerIndex();
    }

    public Stack<Worker> getWorkSequence() {
        Stack<Worker> workers = new Stack<>();
        injectFirstWorkerInfo(workers);

        int month = oncallDate.getMonth();
        for (int date = 2; date <= oncallDate.dateOfMonth(); date++) {
            rotateWorkerIndex();
            if (oncallDate.isHolidayOrWeekend(month, date)) {
                addHolidayOrWeekendWorker(workers);
                continue;
            }
            addWeekdayWorker(workers);
        }
        resetWorkerIndex();
        return workers;
    }

    private void injectFirstWorkerInfo(Stack<Worker> workers) {
        if (oncallDate.isHolidayOrWeekend(oncallDate.getMonth(), 1)) {
            workers.push(weekendWorkers.getWorkerByIndex(weekendWorkerIndex++));
            return;
        }
        workers.push(weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex++));
    }

    private void rotateWorkerIndex() {
        if (weekdayWorkerIndex % weekdayWorkers.getWorkers().size() == 0) {
            weekdayWorkerIndex = 0;
        }
        if (weekendWorkerIndex % weekendWorkers.getWorkers().size() == 0) {
            weekendWorkerIndex = 0;
        }
    }

    private void resetWorkerIndex() {
        weekdayWorkerIndex = 0;
        weekendWorkerIndex = 0;
    }

    private void addWeekdayWorker(Stack<Worker> workers) {
        Worker todayWorker = weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex);
        changeWeekdayWorkSequenceIfWorkerWorkContinuous(workers, todayWorker, weekdayWorkerIndex);
        workers.push(weekdayWorkers.getWorkerByIndex(weekdayWorkerIndex++));
    }

    private void addHolidayOrWeekendWorker(Stack<Worker> workers) {
        Worker todayWorker = weekendWorkers.getWorkerByIndex(weekendWorkerIndex);
        //전날 근무자가 본인이면 다음날 주말 근무자와 순서 변경
        changeWeekendWorkSequenceIfWorkerWorkContinuous(workers, todayWorker, weekendWorkerIndex);
        workers.push(weekendWorkers.getWorkerByIndex(weekendWorkerIndex++));
    }

    private void changeWeekdayWorkSequenceIfWorkerWorkContinuous(Stack<Worker> workers, Worker todayWorker, int weekdayWorkerIndex) {
        if (workers.peek().getName().equals(todayWorker.getName())) {
            changeWeekdayWorkerSequence(weekdayWorkerIndex);
        }
    }

    private void changeWeekendWorkSequenceIfWorkerWorkContinuous(Stack<Worker> workers, Worker todayWorker, int weekendWorkerIndex) {
        if (workers.peek().getName().equals(todayWorker.getName())) {
            changeWeekendWorkerSequence(weekendWorkerIndex);
        }
    }

    private void changeWeekdayWorkerSequence(int weekdayWorkerIndex) {
        weekdayWorkers.changeSequence(weekdayWorkerIndex, weekdayWorkerIndex + 1);
    }

    private void changeWeekendWorkerSequence(int weekendWorkerIndex) {
        weekendWorkers.changeSequence(weekendWorkerIndex, weekendWorkerIndex + 1);
    }
}
