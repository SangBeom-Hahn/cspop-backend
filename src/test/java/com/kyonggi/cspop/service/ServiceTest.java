package com.kyonggi.cspop.service;

import com.kyonggi.cspop.repository.*;
import com.kyonggi.cspop.service.graduate.MiddleService;
import com.kyonggi.cspop.service.graduate.ProposalService;
import com.kyonggi.cspop.service.graduate.SubmitService;
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
    protected SubmitService submitService;

    @Autowired
    protected ProposalService proposalService;

    @Autowired
    protected MiddleService middleService;

    @Autowired
    protected GuideBoardService guideBoardService;

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
    protected GraduationRepository graduationRepository;

    @Autowired
    protected SubmitRepository submitRepository;

    @Autowired
    protected ProposalRepository proposalRepository;

    @Autowired
    protected MiddleRepository middleRepository;

    @Autowired
    protected GuideBoardRepository guideBoardRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;
}
