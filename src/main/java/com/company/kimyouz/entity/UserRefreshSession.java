package com.company.kimyouz.entity;

import com.company.kimyouz.dto.response.ResponseUserDto;
import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 60)
public class UserRefreshSession {

    @Id
    private String sessionId;
    private ResponseUserDto userDto;

}
