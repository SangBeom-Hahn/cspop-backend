package com.kyonggi.cspop.service.dto.graduate.proposal;

import com.kyonggi.cspop.domain.graduate.Type;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
public class ProposalSaveRequestDto {

    private String title;

    private Type type;

    private String qualification;

    private String content;
}
