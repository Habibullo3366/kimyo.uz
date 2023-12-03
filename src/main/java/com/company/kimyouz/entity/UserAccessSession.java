package com.company.kimyouz.entity;

import com.company.kimyouz.dto.response.ResponseUserDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 3600)
public class UserAccessSession {

    @Id
    private String sessionId;
    private ResponseUserDto userDto;

}
