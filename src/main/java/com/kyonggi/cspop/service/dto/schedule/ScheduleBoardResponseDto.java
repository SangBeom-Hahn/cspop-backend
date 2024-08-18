package com.kyonggi.cspop.service.dto.schedule;

import com.kyonggi.cspop.domain.schedule.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleBoardResponseDto {

    private String receive;

    private String proposal;

    private String middleReport;

    private String finalReport;

    private String pass;

    private String other;

    public static ScheduleBoardResponseDto from(Board board) {
        return new ScheduleBoardResponseDto(
                board.getReceive(),
                board.getProposal(),
                board.getMiddleReport(),
                board.getFinalReport(),
                board.getPass(),
                board.getOther()
        );
    }
}
