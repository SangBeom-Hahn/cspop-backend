package com.kyonggi.cspop.service;

import com.kyonggi.cspop.domain.refreshtoken.RefreshToken;
import com.kyonggi.cspop.domain.student.RoleType;
import com.kyonggi.cspop.domain.student.Student;
import com.kyonggi.cspop.exception.IdPasswordMismatchException;
import com.kyonggi.cspop.exception.NoSuchStudentException;
import com.kyonggi.cspop.exception.NoSuchStudentIdException;
import com.kyonggi.cspop.repository.RefreshTokenRepository;
import com.kyonggi.cspop.repository.StudentRepository;
import com.kyonggi.cspop.service.dto.student.TokenResponseDto;
import com.kyonggi.cspop.support.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponseDto login(String loginId, String password) {
        Student findStudent = studentRepository.findByLoginId(loginId)
                .orElseThrow(() -> new NoSuchStudentException(loginId));

        validatePassword(findStudent, password);
        return issueTokenDto(findStudent.getId(), findStudent.getRoleType());
    }

    private void validatePassword(final Student findStudent, final String password) {
        if (isMisMatchPassword(findStudent, password)) {
            throw new IdPasswordMismatchException();
        }
    }

    private boolean isMisMatchPassword(Student findStudent, String password) {
        return !passwordEncoder.matches(password, findStudent.getPassword());
    }

    private TokenResponseDto issueTokenDto(Long studentId, RoleType roleType) {
        Map<String, Object> payload = createPayloadMap(studentId, roleType);

        String accessToken = jwtTokenProvider.createToken(payload);
        RefreshToken refreshToken = createRefreshToken(studentId);
        return TokenResponseDto.of(accessToken, refreshToken.getTokenValue(), studentId);
    }

    private Map<String, Object> createPayloadMap(Long studentId, RoleType roleType) {
        return JwtTokenProvider.payloadBuilder()
                .setSubject(String.valueOf(studentId))
                .put(roleType.name())
                .build();
    }

    private RefreshToken createRefreshToken(final Long studentId) {
        final Student findStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchStudentIdException(studentId));

        final RefreshToken refreshToken = RefreshToken.createBy(findStudent.getId(), () -> UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    public void logout(final String refreshToken) {
        refreshTokenRepository.deleteByTokenValue(refreshToken);
    }
}
