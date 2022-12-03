package com.poxios.bulletin.domain.user.dto;

import com.poxios.bulletin.domain.user.User;
import com.poxios.bulletin.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserSignUpRequestDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    // Enum Validator : https://www.baeldung.com/javax-validations-enums
    // @ValueOfEnum(enumClass = UserRole.class)
    // TODO: Update to official enum validator
    private UserRole role;


    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .build();
    }
}