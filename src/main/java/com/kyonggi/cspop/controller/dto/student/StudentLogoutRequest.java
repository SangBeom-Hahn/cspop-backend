package com.kyonggi.cspop.controller.dto.student;

import com.kyonggi.cspop.controller.dto.ValidateMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kyonggi.cspop.controller.dto.ValidateMessage.EMPTY_MESSAGE;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentLogoutRequest {

    @NotBlank(message = EMPTY_MESSAGE)
    private String refreshToken;
}
