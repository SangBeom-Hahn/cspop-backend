package com.kyonggi.cspop.controller.dto.student;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentLoginRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = SIGN_UP_MAX_SIZE, message = ID_SIZE_MESSAGE)
    private String loginId;

    @NotBlank(message = EMPTY_MESSAGE)
    @Pattern(
            regexp = PASSWORD_PATTERN,
            message = PASSWORD_MESSAGE
    )
    private String password;
}
