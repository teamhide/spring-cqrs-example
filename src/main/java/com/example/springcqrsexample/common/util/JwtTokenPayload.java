package com.example.springcqrsexample.common.util;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtTokenPayload {
    private Long userId;
    private Boolean refresh;

    @Builder
    public JwtTokenPayload(Long userId, Boolean refresh) {
        this.userId = userId;
        this.refresh = refresh;
    }
}
