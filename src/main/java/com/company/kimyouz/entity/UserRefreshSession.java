package com.company.kimyouz.entity;

//import com.Jwts.JwtsFilter.dto.Response.UsersResponseDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(timeToLive = 120)
public class UserRefreshSession {

    @Id
    private String sessionId;
    private ResponseUserDto usersDto;
}
