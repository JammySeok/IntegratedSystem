package project.IntegratedSystem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN(0, "ROLE_ADMIN", "관리자"),
    GUEST(1, "ROLE_GUEST", "게스트"),
    USER(2, "ROLE_USER", "유저");

    private final Integer code;
    private final String key;
    private final String description;
}