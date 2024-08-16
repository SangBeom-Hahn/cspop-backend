package com.kyonggi.cspop.domain.refreshtoken;

import com.kyonggi.cspop.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseEntity {

    private static final int EXPIRED_DAYS = 7;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    @Column(name = "token_value", unique = true, nullable = false)
    private String tokenValue;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "expired_time")
    private LocalDateTime expiredTime;

    private RefreshToken(final String tokenValue, final Long memberId, final LocalDateTime expiredTime) {
        this.tokenValue = tokenValue;
        this.memberId = memberId;
        this.expiredTime = expiredTime;
    }

    public static RefreshToken createBy(final Long memberId, final RefreshTokenGenerator generator) {
        return new RefreshToken(
                generator.generate(),
                memberId,
                LocalDateTime.now().plusDays(EXPIRED_DAYS)
        );
    }
}
