package com.kyonggi.cspop.controller.dto.graduate;

import com.kyonggi.cspop.domain.graduate.Type;
import com.kyonggi.cspop.service.dto.graduate.finals.FinalSaveRequestDto;
import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveRequestDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FinalSaveRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String title;

    @NotBlank(message = EMPTY_MESSAGE)
    private String type;

    @NotBlank(message = EMPTY_MESSAGE)
    private String qualification;

    @NotNull(message = EMPTY_MESSAGE)
    private Integer page;

    public FinalSaveRequestDto toServiceDto() {
        return new FinalSaveRequestDto(
                title,
                Type.from(type),
                qualification,
                page
        );
    }
}
