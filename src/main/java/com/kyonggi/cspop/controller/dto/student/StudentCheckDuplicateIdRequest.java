package com.kyonggi.cspop.controller.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentCheckDuplicateIdRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = SIGN_UP_MAX_SIZE, message = ID_SIZE_MESSAGE)
    private String loginId;
}
