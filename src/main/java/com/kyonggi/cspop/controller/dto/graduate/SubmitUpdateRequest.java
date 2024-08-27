package com.kyonggi.cspop.controller.dto.graduate;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import com.kyonggi.cspop.service.dto.graduate.submit.SubmitUpdateRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitUpdateRequest {

    @NotNull(message = EMPTY_MESSAGE)
    private LocalDate graduateDate;

    @NotNull(message = EMPTY_MESSAGE)
    private Boolean completion;

    @NotNull(message = EMPTY_MESSAGE)
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
