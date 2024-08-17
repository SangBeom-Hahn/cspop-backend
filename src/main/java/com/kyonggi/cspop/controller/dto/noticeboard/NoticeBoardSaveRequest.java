package com.kyonggi.cspop.controller.dto.noticeboard;

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
public class NoticeBoardSaveRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String content;

    @NotNull(message = EMPTY_MESSAGE)
    private Boolean fix;

    @NotNull(message = EMPTY_MESSAGE)
    private String title;
}
