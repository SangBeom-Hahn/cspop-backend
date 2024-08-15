package com.kyonggi.cspop.controller.dto.student;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentLogoutRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String refreshToken;
}
