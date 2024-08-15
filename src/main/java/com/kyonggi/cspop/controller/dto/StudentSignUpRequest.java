package com.kyonggi.cspop.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSignUpRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = NUMBER_UP_MAX_SIZE, message = NUMBER_SIZE_MESSAGE)
    private String number;

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = SIGN_UP_MAX_SIZE, message = ID_SIZE_MESSAGE)
    private String loginId;

    @NotBlank(message = EMPTY_MESSAGE)
    @Pattern(
            regexp = PASSWORD_PATTERN,
            message = PASSWORD_MESSAGE
    )
    private String password;

    @NotNull(message = EMPTY_MESSAGE)
    private LocalDate birth;

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = NAME_MAX_SIZE, message = DEPARTMENT_SIZE_MESSAGE)
    private String department;

    @NotBlank(message = EMPTY_MESSAGE)
    private String grade;

    @NotBlank(message = EMPTY_MESSAGE)
    private String phoneNumber;

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = NAME_MAX_SIZE, message = NAME_SIZE_MESSAGE)
    private String name;

    @NotBlank(message = EMPTY_MESSAGE)
    private String email;

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = SIGN_UP_MAX_SIZE, message = CLASSIFICATION_SIZE_MESSAGE)
    private String classification;
}
