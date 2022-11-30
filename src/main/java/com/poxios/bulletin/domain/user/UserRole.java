package com.poxios.bulletin.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    //    스프링 시큐리티에는 권한 코드에 항상 ROLE_이 앞에 있어야한다.
    ROLE_GUEST("ROLE_GUEST"),
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String role;
}