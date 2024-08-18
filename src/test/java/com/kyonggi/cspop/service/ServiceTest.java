package com.kyonggi.cspop.service;

import com.kyonggi.cspop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public abstract class ServiceTest {

    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected ScheduleService scheduleService;

    @Autowired
    protected NoticeBoardService noticeBoardService;

    @Autowired
    protected StudentRepository studentRepository;

    @Autowired
    protected NoticeBoardRepository noticeBoardRepository;

    @Autowired
    protected RefreshTokenRepository refreshTokenRepository;

    @Autowired
    protected CommentRepository commentRepository;

    @Autowired
    protected ScheduleRepository scheduleRepository;

    @Autowired
    protected ScheduleBoardRepository scheduleBoardRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;
}
