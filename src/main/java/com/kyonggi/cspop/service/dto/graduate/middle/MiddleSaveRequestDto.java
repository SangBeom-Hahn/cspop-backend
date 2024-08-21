package com.kyonggi.cspop.service.dto.graduate.middle;

import com.kyonggi.cspop.domain.graduate.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MiddleSaveRequestDto {

    private String title;

    private Type type;

    private String text;

    private String plan;
}
