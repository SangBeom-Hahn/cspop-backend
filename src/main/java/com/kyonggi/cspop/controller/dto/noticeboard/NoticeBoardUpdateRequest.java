package com.kyonggi.cspop.controller.dto.noticeboard;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeBoardUpdateRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String content;

    @NotNull(message = EMPTY_MESSAGE)
    private Boolean fix;

    @NotBlank(message = EMPTY_MESSAGE)
    private String title;
}
