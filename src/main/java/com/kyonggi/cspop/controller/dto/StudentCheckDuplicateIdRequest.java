package com.kyonggi.cspop.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCheckDuplicateIdRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    @Size(min = SIGN_UP_MIN_SIZE, max = SIGN_UP_MAX_SIZE, message = ID_SIZE_MESSAGE)
    private String loginId;
}
