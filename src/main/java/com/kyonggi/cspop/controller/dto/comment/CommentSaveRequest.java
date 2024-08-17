package com.kyonggi.cspop.controller.dto.comment;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSaveRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String content;
}
