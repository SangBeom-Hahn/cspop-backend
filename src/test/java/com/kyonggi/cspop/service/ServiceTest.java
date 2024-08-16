package com.kyonggi.cspop.service;

import com.kyonggi.cspop.repository.RefreshTokenRepository;
import com.kyonggi.cspop.repository.StudentRepository;
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
    protected StudentRepository studentRepository;

    @Autowired
    protected RefreshTokenRepository refreshTokenRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;
}
