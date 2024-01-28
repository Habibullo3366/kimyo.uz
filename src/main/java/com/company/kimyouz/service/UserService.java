package com.company.kimyouz.service;


import com.company.kimyouz.dto.*;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.UserAccessSession;
import com.company.kimyouz.entity.UserRefreshSession;
import com.company.kimyouz.entity.Users;
import com.company.kimyouz.repository.UserAccessSessionRepository;
import com.company.kimyouz.repository.UserRefreshSessionRepository;
import com.company.kimyouz.security.JwtFilter;
import com.company.kimyouz.security.JwtUtils;
import com.company.kimyouz.service.mapper.UserMapper;
import com.company.kimyouz.repository.UserRepository;
import com.company.kimyouz.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final UserAccessSessionRepository accessSessionRepository;
    private final UserRefreshSessionRepository refreshSessionRepository;
    private final JwtUtils jwtUtils;


    public ResponseDto<ResponseUserDto> createEntity(RequestUserDto dto) {
        List<ErrorDto> errors = this.userValidation.userValid(dto);
        if (!errors.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errors)
                    .build();
        }

        try {
            Users users = this.userMapper.toEntity(dto);
            users.setCreatedAt(LocalDateTime.now());
            return ResponseDto.<ResponseUserDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.userMapper.toDto(
                                    this.userRepository.save(users)
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-2)
                    .message(String.format("User while saving error message :: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseUserDto> getEntity(Integer entityId) {
        Optional<Users> optionalUser = this.userRepository
                .findByUserIdAndDeletedAtIsNullOrderByCardsAsc(entityId);
        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-1)
                    .message(String.format("User with %d:id is not found!", entityId))
                    .build();
        }
        return ResponseDto.<ResponseUserDto>builder()
                .success(true)
                .message("OK")
                .content(
                        this.userMapper.toDtoWithCard(optionalUser.get())
                )
                .build();
    }


    public ResponseDto<ResponseUserDto> updateEntity(Integer entityId, RequestUserDto dto) {
        try {
            Optional<Users> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.<ResponseUserDto>builder()
                        .code(-1)
                        .message(String.format("User wiht %d:id is not found!", entityId))
                        .build();
            }
            return ResponseDto.<ResponseUserDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.userMapper.toDto(
                                    this.userRepository.save(
                                            this.userMapper.updateUser(dto, optionalUser.get())
                                    )
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-1)
                    .message(String.format("User with %d:id is not found!", entityId))
                    .build();
        }
    }


    public ResponseDto<ResponseUserDto> deleteEntity(Integer entityId) {
        Optional<Users> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-1)
                    .message(String.format("User with %d:id is not found!", entityId))
                    .build();
        }
        Users users = optionalUser.get();
        users.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseUserDto>builder()
                .success(true)
                .message("OK")
                .content(this.userMapper.toDto(
                                this.userRepository.save(users)
                        )
                )
                .build();
    }

    public ResponseDto<List<ResponseUserDto>> getAll() {
        return ResponseDto.<List<ResponseUserDto>>builder()
                .success(true)
                .message("OK")
                .content(
                        this.userRepository.findAllByDeletedAtIsNull()
                                .stream()
                                .map(this.userMapper::toDto)
                                .toList()
                )
                .build();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> users = this.userRepository.findByUsernameAndDeletedAtIsNull(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException(
                    String.format("User with %s username is not found", username)
            );
        }

        return this.userMapper.toDto(users.get());
    }


    public ResponseDto<TokenResponseDto> logIn(LogInResponseDto dto) {

        Optional<Users> users = this.userRepository.findByUsernameAndDeletedAtIsNull(dto.getUsername());

        if(users.isEmpty()){
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-1)
                    .message("Username is not found")
                    .build();
        }

        String access = UUID.randomUUID().toString();
        this.accessSessionRepository.save(
                UserAccessSession.builder()
                        .sessionId(access)
                        .usersDto(this.userMapper.toDto(users.get()))
                        .build()
        );

        String refresh = UUID.randomUUID().toString();
        this.refreshSessionRepository.save(
                UserRefreshSession.builder()
                        .sessionId(refresh)
                        .usersDto(this.userMapper.toDto(users.get()))
                        .build()
        );

        return ResponseDto.<TokenResponseDto>builder()
                .message("OK")
                .success(true)
                .content(TokenResponseDto.builder()
                        .accessToken(jwtUtils.generateAccessToken(access))
                        .refreshToken(jwtUtils.generateRefreshToken(refresh))
                        .build())
                .build();
    }

    public ResponseDto<Void> logOut(LogOutResponseDto dto) {

        String sub = this.jwtUtils.getClaim("sub" , dto.getToken() , String.class);

        Optional<UserAccessSession> users = this.accessSessionRepository.findById(sub);

        if(users.isPresent()) {
            Users user = this.userMapper.convert(users.get().getUsersDto());
            this.userRepository.save(user);
        }

        return ResponseDto.<Void>builder()
                .success(true)
                .message("OK")
                .build();
    }

    public ResponseDto<TokenResponseDto> refresh(String token) {

        String sub = this.jwtUtils.getClaim("sub" , token , String.class);

        Optional<UserRefreshSession> users = this.refreshSessionRepository.findById(sub);

        ResponseUserDto dto = users.get().getUsersDto();

        Optional<UserAccessSession> userAccessSession = this.accessSessionRepository.findUserAccessSessionByUsersDto(dto);

        UserAccessSession accessSession = userAccessSession.get();

        accessSession.setSessionId(UUID.randomUUID().toString());

        this.accessSessionRepository.save(accessSession);

        return ResponseDto.<TokenResponseDto>builder()
                .content(TokenResponseDto.builder()
                        .accessToken(this.jwtUtils.generateAccessToken(accessSession.getSessionId()))
                        .refreshToken(token)
                        .build())
                .message("OK")
                .build();
    }
}
