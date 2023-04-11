package com.taskmanagmentsystem.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDto {
        @JsonProperty("access_token")
        String accessToken;
        @JsonProperty("refresh_token")
        String refreshToken;
}
