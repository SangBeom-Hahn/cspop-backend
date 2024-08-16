package com.kyonggi.cspop.repository;

import com.kyonggi.cspop.config.JpaAuditingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaAuditingConfig.class)
public abstract class RepositoryTest {

    @Autowired
    protected StudentRepository studentRepository;

    @Autowired
    protected NoticeBoardRepository noticeBoardRepository;

    @Autowired
    protected CommentRepository commentRepository;
}
