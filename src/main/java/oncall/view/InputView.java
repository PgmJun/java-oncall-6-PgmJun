package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import oncall.view.dto.OncallDateDto;
import oncall.view.validator.InputValidator;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public OncallDateDto readOncallDate() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = Console.readLine();
        inputValidator.validateDateFormat(input);

        String[] oncallDate = input.split(",");
        return new OncallDateDto(Integer.parseInt(oncallDate[0]), oncallDate[1]);
    }

    public List<String> readWeekdayWorkers() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Arrays.stream(Console.readLine().split(","))
                .collect(Collectors.toList());
    }

    public List<String> readWeekendWorkers() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        return Arrays.stream(Console.readLine().split(","))
                .collect(Collectors.toList());
    }
}
