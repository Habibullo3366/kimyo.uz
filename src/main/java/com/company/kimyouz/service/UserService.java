package com.company.kimyouz.service;


import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.User;
import com.company.kimyouz.service.mapper.UserMapper;
import com.company.kimyouz.repository.UserRepository;
import com.company.kimyouz.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final UserRepository userRepository;


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
            return ResponseDto.<ResponseUserDto>builder()
                    .success(true)
                    .message("OK")
                    .data(
                            this.userMapper.toDto(
                                    this.userRepository.save(user)
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
        Optional<User> optionalUser = this.userRepository
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
                .data(
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
                    .data(this.userMapper.toDto(
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
                .data(this.userMapper.toDto(
                                this.userRepository.save(user)
                        )
                )
                .build();
    }

    public ResponseDto<List<ResponseUserDto>> getAll() {
        return ResponseDto.<List<ResponseUserDto>>builder()
                .success(true)
                .message("OK")
                .data(
                        this.userRepository.findAllByDeletedAtIsNull()
                                .stream()
                                .map(this.userMapper::toDto)
                                .toList()
                )
                .build();
    }


}
