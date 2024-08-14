package com.kyonggi.cspop.config;

import com.kyonggi.cspop.TestDataInit;
import com.kyonggi.cspop.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyConfig {
    @Bean
    public TestDataInit testDataInit(StudentRepository studentRepository) {
        return new TestDataInit(studentRepository);
    }
}
