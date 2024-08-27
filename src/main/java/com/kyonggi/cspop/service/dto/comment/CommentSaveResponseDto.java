package com.kyonggi.cspop.service.dto.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentSaveResponseDto {

    private Long id;

    public static CommentSaveResponseDto from(final Long id) {
        return new CommentSaveResponseDto(id);
    }
}
