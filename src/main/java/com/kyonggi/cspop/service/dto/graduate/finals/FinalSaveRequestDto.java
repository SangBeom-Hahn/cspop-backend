package com.kyonggi.cspop.service.dto.graduate.finals;

import com.kyonggi.cspop.domain.graduate.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FinalSaveRequestDto {

    private String title;

    private Type type;

    private String qualification;

    private Integer page;
}
