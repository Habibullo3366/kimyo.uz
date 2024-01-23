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
                            this.userMapper.toDto(this.userRepository.save(user))
                    )
                    .build();
        } catch (Exception e) {

            return ResponseDto.<ResponseUserDto>builder()
                    .code(-2)
                    .message(String.format("Error while creating User: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseUserDto> getEntity(Integer entityId) {
        try {
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
        } catch (Exception e) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-3)
                    .message(String.format("Error while getting User: %s", e.getMessage()))
                    .build();
        }

    }


    public ResponseDto<ResponseUserDto> updateEntity(Integer entityId, RequestUserDto dto) {
        try {
            Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.<ResponseUserDto>builder()
                        .code(-1)
                        .message(String.format("User with %d ID is not found!", entityId))
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
                    .message(String.format("Error while updating User: %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseUserDto> deleteEntity(Integer entityId) {
        try {
            Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(entityId);
            if (optionalUser.isEmpty()) {
                return ResponseDto.<ResponseUserDto>builder()
                        .code(-1)
                        .message(String.format("User with %d ID not found!", entityId))
                        .build();
            }
            optionalUser.get().setDeletedAt(LocalDateTime.now());
            return ResponseDto.<ResponseUserDto>builder()
                    .success(true)
                    .message("OK")
                    .content(this.userMapper.toDto(
                                    this.userRepository.save(optionalUser.get())
                            )
                    )
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ResponseUserDto>builder()
                    .code(-3)
                    .message(String.format("Error while deleting User: %s", e.getMessage()))
                    .build();
        }

    }

    public ResponseDto<List<ResponseUserDto>> getAll() {
        try {
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
        } catch (Exception e) {
            return ResponseDto.<List<ResponseUserDto>>builder()
                    .code(-3)
                    .message(String.format("Error while getting all Users: %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ResponseTokenDto> logIn(LogInDto logIn) {
        try {
            Optional<User> optionalUser = this.userRepository
                    .findByUsernameAndDeletedAtIsNull(logIn.getUsername());

            if (optionalUser.isEmpty()) {
                return ResponseDto.<ResponseTokenDto>builder()
                        .code(-1)
                        .message(String.format("User with %s username is not found!", logIn.getUsername()))
                        .build();
            }

            optionalUser.get().setEnabled(true);

            String accessSessionId = UUID.randomUUID().toString();
            this.userAccessSessionRepository.save(
                    UserAccessSession.builder()
                            .sessionId(accessSessionId)
                            .userDto(this.userMapper.toDto(this.userRepository.save(optionalUser.get())))
                            .build()
            );

            String refreshSessionId = UUID.randomUUID().toString();
            this.userRefreshSessionRepository.save(
                    UserRefreshSession.builder()
                            .sessionId(refreshSessionId)
                            .userDto(this.userMapper.toDto(optionalUser.get()))
                            .build()
            );

            log.info(String.format("User %s logged in successfully", optionalUser.get().getUsername()));
            return ResponseDto.<ResponseTokenDto>builder()
                    .success(true)
                    .message("OK")
                    .content(ResponseTokenDto.builder()
                            .accessToken(this.jwtUtils.generateAccessToken(accessSessionId))
                            .refreshToken(this.jwtUtils.generateRefreshToken(refreshSessionId))
                            .build())
                    .build();
        }
        catch (Exception e) {
            return ResponseDto.<ResponseTokenDto>builder()
                    .code(-3)
                    .message(String.format("Error while login: %s", e.getMessage()))
                    .build();
        }

    }

    public ResponseDto<Void> logout(LogOutDto logOut) {
        try {
            Optional<UserAccessSession> userAccessSession = this.userAccessSessionRepository.findById(
                    this.jwtUtils.getClaim("sub", logOut.getToken(), String.class)
            );
            userAccessSession.ifPresent(
                    accessSession -> {
                        log.info(String.format("User with %s username logout.", userAccessSession.get().getUserDto().getUsername()));
                        this.userRepository
                                .save(
                                        this.userMapper.convertUsers(accessSession.getUserDto())
                                );
                    }
            );
            return ResponseDto.<Void>builder()
                    .success(true)
                    .message("")
                    .build();
        }
        catch (Exception e) {
            return ResponseDto.<Void>builder()
                    .code(-3)
                    .message(String.format("Error while logout %s", e.getMessage()))
                    .build();
        }
    }


    public ResponseDto<ResponseTokenDto> refreshToken(String token) {
        String sub = this.jwtUtils.getClaim("sub", token, String.class);

        Optional<UserRefreshSession> users = this.userRefreshSessionRepository.findById(sub);

        ResponseUserDto dto = users.get().getUserDto();

        Optional<UserAccessSession> userAccessSession = this.userAccessSessionRepository.findUserAccessSessionByUserDto(dto);

        UserAccessSession accessSession = userAccessSession.get();

        accessSession.setSessionId(UUID.randomUUID().toString());

        this.userAccessSessionRepository.save(accessSession);

        return ResponseDto.<ResponseTokenDto>builder()
                .content(ResponseTokenDto.builder()
                        .accessToken(this.jwtUtils.generateAccessToken(accessSession.getSessionId()))
                        .refreshToken(token)
                        .build())
                .message("OK")
                .build();
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