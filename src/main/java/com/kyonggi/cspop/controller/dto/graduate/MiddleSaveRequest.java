package com.kyonggi.cspop.controller.dto.graduate;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import com.kyonggi.cspop.domain.graduate.Type;
import com.kyonggi.cspop.service.dto.graduate.middle.MiddleSaveRequestDto;
import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveRequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MiddleSaveRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String title;

    @NotBlank(message = EMPTY_MESSAGE)
    private String type;

    @NotBlank(message = EMPTY_MESSAGE)
    private String text;

    @NotBlank(message = EMPTY_MESSAGE)
    private String plan;

    public MiddleSaveRequestDto toServiceDto() {
        return new MiddleSaveRequestDto(
                title,
                Type.from(type),
                text,
                plan
        );
    }
}
