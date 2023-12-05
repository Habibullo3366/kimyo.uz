package com.company.kimyouz.service;


import com.company.kimyouz.dto.*;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.Authorities;
import com.company.kimyouz.entity.User;
import com.company.kimyouz.entity.UserAccessSession;
import com.company.kimyouz.entity.UserRefreshSession;
import com.company.kimyouz.repository.AuthorityRepository;
import com.company.kimyouz.repository.UserAccessSessionRepository;
import com.company.kimyouz.repository.UserRefreshSessionRepository;
import com.company.kimyouz.security.JwtUtils;
import com.company.kimyouz.service.mapper.UserMapper;
import com.company.kimyouz.repository.UserRepository;
import com.company.kimyouz.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserAccessSessionRepository userAccessSessionRepository;
    private final UserRefreshSessionRepository userRefreshSessionRepository;


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
            User user = this.userMapper.toEntity(dto);
            user.setCreatedAt(LocalDateTime.now());
            user = this.userRepository.save(user);
            user.setAuthorities(
                    List.of(
                            this.authorityRepository.save(
                                    Authorities.builder()
                                            .userId(user.getUserId())
                                            .username(user.getUsername())
                                            .authority("USER")
                                            .build()
                            )
                    )
            );
            return ResponseDto.<ResponseUserDto>builder()
                    .success(true)
                    .message("OK")
                    .content(
                            this.userMapper.toDto(user)
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
        Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
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

        List<ErrorDto> errorList = this.userValidation.userValidPutMethod(dto);
        if (!errorList.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errorList(errorList)
                    .build();
        }

        try {
            Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
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
        Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-1)
                    .message(String.format("User with %d:id is not found!", entityId))
                    .build();
        }
        User user = optionalUser.get();
        user.setDeletedAt(LocalDateTime.now());
        return ResponseDto.<ResponseUserDto>builder()
                .success(true)
                .message("OK")
                .content(this.userMapper.toDto(
                                this.userRepository.save(user)
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

    public ResponseDto<ResponseTokenDto> logIn(LogInDto logIn) {
        Optional<User> optionalUser = this.userRepository
                .findByUsernameAndDeletedAtIsNull(logIn.getUsername());

        if (optionalUser.isEmpty()) {
            return ResponseDto.<ResponseTokenDto>builder()
                    .code(-1)
                    .message(String.format("User with %s username is not found!", logIn.getUsername()))
                    .build();
        }

        User user = optionalUser.get();
        user.setEnabled(true);
        user = this.userRepository.save(user);

        String accessSessionId = UUID.randomUUID().toString();
        this.userAccessSessionRepository.save(
                UserAccessSession.builder()
                        .sessionId(accessSessionId)
                        .userDto(this.userMapper.toDto(user))
                        .build()
        );

        String refreshSessionId = UUID.randomUUID().toString();
        this.userRefreshSessionRepository.save(
                UserRefreshSession.builder()
                        .sessionId(refreshSessionId)
                        .userDto(this.userMapper.toDto(user))
                        .build()
        );

        log.info(String.format("User %s logged in successfully", user.getUsername()));
        return ResponseDto.<ResponseTokenDto>builder()
                .success(true)
                .message("OK")
                .content(ResponseTokenDto.builder()
                        .accessToken(this.jwtUtils.generateAccessToken(accessSessionId))
                        .refreshToken(this.jwtUtils.generateRefreshToken(refreshSessionId))
                        .build())
                .build();
    }

    public ResponseDto<Void> logout(LogOutDto logOut) {
        Optional<UserAccessSession> userAccessSession = this.userAccessSessionRepository.findById(
                this.jwtUtils.getClaim("sub", logOut.getToken(), String.class)
        );
        userAccessSession.ifPresent(
                accessSession -> {
                    log.info(String.format("User with %s username logout.", userAccessSession.get().getUserDto().getUsername()));
                    ResponseUserDto userDto = accessSession.getUserDto();
                    userDto.setEnabled(false);
                    this.userRepository
                            .save(
                                    this.userMapper.convertUsers(userDto)
                            );
                }
        );
        return ResponseDto.<Void>builder()
                .success(true)
                .message("")
                .build();
    }


    public ResponseDto<ResponseTokenDto> refreshAccessToken(String originalToken) {
        if (this.jwtUtils.isValid(originalToken)) {
            return this.userRefreshSessionRepository.findById(
                    this.jwtUtils.getClaim("sub", originalToken, String.class)
            ).map(refreshSession -> {
                String accessSessionId = UUID.randomUUID().toString();
                this.userAccessSessionRepository.save(
                        UserAccessSession.builder()
                                .sessionId(accessSessionId)
                                .userDto(refreshSession.getUserDto())
                                .build()
                );
                return ResponseDto.<ResponseTokenDto>builder()
                        .success(true)
                        .message("OK")
                        .content(ResponseTokenDto.builder()
                                .accessToken(this.jwtUtils.generateAccessToken(accessSessionId))
                                .refreshToken(originalToken)
                                .build())
                        .build();
            }).orElse(ResponseDto.<ResponseTokenDto>builder()
                    .code(-1)
                    .message("User is not found!")
                    .build());
        } else {
            return ResponseDto.<ResponseTokenDto>builder()
                    .code(-5)
                    .message("Token is not valid!")
                    .build();
        }
    }

    @Override
    public ResponseUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsernameAndDeletedAtIsNullAndEnabledIsTrue(username)
                .map(this.userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException(
                                String.format("User with %s username is not found!", username)
                        )
                );
    }
}
