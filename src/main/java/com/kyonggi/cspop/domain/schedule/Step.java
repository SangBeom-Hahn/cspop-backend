package com.kyonggi.cspop.domain.schedule;

public enum Step {
    RECEIVE("신청 접수"),
    PROPOSAL("제안서"),
    MIDDLE("중간보고서"),
    FINAL("최종보고서"),
    OTHER_QUALIFICATION("기타 자격"),
    PASS("최종 통과")
    ;

    private final String name;

    Step(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
