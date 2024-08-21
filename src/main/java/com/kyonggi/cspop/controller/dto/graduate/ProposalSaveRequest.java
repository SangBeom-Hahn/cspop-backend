package com.kyonggi.cspop.controller.dto.graduate;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import com.kyonggi.cspop.domain.graduate.Type;
import com.kyonggi.cspop.service.dto.graduate.proposal.ProposalSaveRequestDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProposalSaveRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String title;

    @NotBlank(message = EMPTY_MESSAGE)
    private String type;

    @NotBlank(message = EMPTY_MESSAGE)
    private String qualification;

    @NotBlank(message = EMPTY_MESSAGE)
    private String content;

    public ProposalSaveRequestDto toServiceDto() {
        return new ProposalSaveRequestDto(
                title,
                Type.from(type),
                qualification,
                content
        );
    }
}
