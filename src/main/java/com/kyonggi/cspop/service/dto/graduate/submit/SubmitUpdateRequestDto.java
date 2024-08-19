package com.kyonggi.cspop.service.dto.graduate.submit;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SubmitUpdateRequestDto {

    private LocalDate graduateDate;

    private Boolean completion;

    private Boolean approve;

    private String reason;
}
