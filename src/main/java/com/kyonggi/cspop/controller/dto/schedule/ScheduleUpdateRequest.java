package com.kyonggi.cspop.controller.dto.schedule;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateRequest {

    @NotNull(message = EMPTY_MESSAGE)
    private LocalDate startDate;

    @NotNull(message = EMPTY_MESSAGE)
    private LocalDate endDate;
}
