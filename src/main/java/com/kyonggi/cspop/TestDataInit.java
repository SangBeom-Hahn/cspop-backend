package com.kyonggi.cspop;

import com.kyonggi.cspop.domain.student.*;
import com.kyonggi.cspop.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@RequiredArgsConstructor
public class TestDataInit {

    private final StudentRepository studentRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        Student student = new Student(
                "201811111",
                "123",
                "$2a$10$tjNt0LH5iFzk1X0sxtTS5eA6cSLkZ1NGw8IIqQL4h.pnxx.vCD/GK",
                LocalDate.of(1999, 9, 17),
                Department.AI,
                Grade.FIRTH,
                PhoneNumber.from("010-1111-1111"),
                "dummy",
                Email.from("1@naver.com"),
                Classification.UNDERGRADUATE_STUDENT
        );
        studentRepository.save(student);
    }
}
