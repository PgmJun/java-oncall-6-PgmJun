package oncall.view;

import java.util.List;
import oncall.domain.OncallDate;
import oncall.domain.Worker;

public class OutputView {
    private final static String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(Exception exception) {
        System.out.println(ERROR_PREFIX + exception.getMessage());
    }

    public void printOncallList(List<Worker> workSequence, OncallDate oncallDate) {
        System.out.println();

        int month = oncallDate.getMonth();
        for(int i = 1; i <= oncallDate.dateOfMonth(); i++) {
            if(oncallDate.isHolyDay(month, i)) {
                System.out.println(String.format("%d월 %d일 %s(휴일) %s", month, i, checkDayOfWeekString(oncallDate.calculateDayOfWeekIndex(i)), workSequence.get(i-1).getName()));
                continue;
            }
            System.out.println(String.format("%d월 %d일 %s %s", month, i, checkDayOfWeekString(oncallDate.calculateDayOfWeekIndex(i)), workSequence.get(i-1).getName()));
        }
    }

    private String checkDayOfWeekString(int dayOfWeekIndex) {
        if(dayOfWeekIndex == 0) {
            return "월";
        } else if(dayOfWeekIndex == 1) {
            return "화";
        }else if(dayOfWeekIndex == 2) {
            return "수";
        }else if(dayOfWeekIndex == 3) {
            return "목";
        }else if(dayOfWeekIndex == 4) {
            return "금";
        }else if(dayOfWeekIndex == 5) {
            return "토";
        }
        return "일";
    }
}
