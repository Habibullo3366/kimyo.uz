package com.company.kimyouz.service;


import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.Authorities;
import com.company.kimyouz.entity.User;
import com.company.kimyouz.repository.AuthorityRepository;
import com.company.kimyouz.service.mapper.UserMapper;
import com.company.kimyouz.repository.UserRepository;
import com.company.kimyouz.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;


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
            this.authorityRepository.save(
                    Authorities.builder()
                            .userId(user.getUserId())
                            .username(user.getUsername())
                            .authority("USER")
                            .build()
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
