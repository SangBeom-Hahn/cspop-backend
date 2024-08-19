package com.kyonggi.cspop.controller.dto.graduate;

import com.kyonggi.cspop.service.dto.graduate.submit.SubmitUpdateRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitUpdateRequest {

    @NotNull
    private LocalDate graduateDate;

    @NotNull
    private Boolean completion;

    @NotNull
    private Boolean approve;

    private String reason;

    public SubmitUpdateRequestDto toServiceDto() {
        return new SubmitUpdateRequestDto(
                graduateDate,
                completion,
                approve,
                reason
        );
    }
}
