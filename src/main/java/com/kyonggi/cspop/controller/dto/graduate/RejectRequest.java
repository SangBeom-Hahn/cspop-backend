package com.kyonggi.cspop.controller.dto.graduate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RejectRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String reason;
}
