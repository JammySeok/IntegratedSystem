package project.IntegratedSystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeePosition {

    STAFF(1, "사원"),
    ASSISTANT_MANAGER(2, "주임"),
    MANAGER(3, "대리"),
    DEPUTY_GENERAL_MANAGER(4, "과장"),
    GENERAL_MANAGER(5, "차장"),
    DIRECTOR(6, "부장"),
    EXECUTIVE_DIRECTOR(7, "이사"),
    MANAGING_DIRECTOR(8, "상무"),
    SENIOR_MANAGING_DIRECTOR(9, "전무"),
    PRESIDENT(10, "사장");

    private final int code;
    private final String title;
}
