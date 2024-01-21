package com.company.kimyouz.user;

import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.Authorities;
import com.company.kimyouz.entity.User;
import com.company.kimyouz.repository.AuthorityRepository;
import com.company.kimyouz.repository.UserAccessSessionRepository;
import com.company.kimyouz.repository.UserRefreshSessionRepository;
import com.company.kimyouz.repository.UserRepository;
import com.company.kimyouz.security.JwtUtils;
import com.company.kimyouz.service.UserService;
import com.company.kimyouz.service.mapper.UserMapper;
import com.company.kimyouz.service.validation.UserValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;


import static java.util.Optional.*;
import static org.mockito.Mockito.*;

public class TestUserService {
    private UserService userService;
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserValidation userValidation;
    private JwtUtils jwtUtils;
    private AuthorityRepository authorityRepository;
    private UserAccessSessionRepository userAccessSessionRepository;
    private UserRefreshSessionRepository userRefreshSessionRepository;

    @BeforeEach
    public void initObject() {
        userMapper = mock(UserMapper.class);
        userValidation = mock(UserValidation.class);
        jwtUtils = mock(JwtUtils.class);
        authorityRepository = mock(AuthorityRepository.class);
        userAccessSessionRepository = mock(UserAccessSessionRepository.class);
        userRefreshSessionRepository = mock(UserRefreshSessionRepository.class);
        userRepository = mock(UserRepository.class);
        userService = new UserService(jwtUtils, userMapper, userValidation, userRepository, authorityRepository, userAccessSessionRepository, userRefreshSessionRepository);
    }

    @Test
    public void testCreateUserPositive() {

        User user = User.builder()
                .userId(1)
                .firstname("Kamol")
                .lastname("Turaqulov")
                .age(20)
                .username("Kamol2103")
                .createdAt(LocalDateTime.now())
                .password("qwe123")
                .build();

        when(userValidation.userValid(any())).thenReturn(new ArrayList<>());

        when(userMapper.toEntity(any())).thenReturn(user);

        when(userRepository.save(any())).thenReturn(user);

        when(authorityRepository.save(any())).thenReturn(Authorities.builder()
                .authority("User")
                .userId(1)
                .username("Kamol2103")
                .id(1)
                .build());

        when(userMapper.toDto(any())).thenReturn(ResponseUserDto.builder()
                .userId(1)
                .firstname("Kamol")
                .lastname("Turaqulov")
                .age(20)
                .username("Kamol2103")
                .createdAt(LocalDateTime.now())
                .password("qwe123")
                .build());
        ResponseDto<ResponseUserDto> response = this.userService.createEntity(any());

        Assertions.assertTrue(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned");


        verify(userValidation, times(1)).userValid(any());
        verify(userMapper, times(1)).toEntity(any());
        verify(userRepository, times(1)).save(any());
        verify(userMapper, times(1)).toDto(any());
        verify(authorityRepository, times(1)).save(any());


    }

    @Test
    public void testCreateUserException() {

        when(this.userRepository.save(any())).thenReturn(RuntimeException.class);

        ResponseDto<ResponseUserDto> response = this.userService.createEntity(any());

        Assertions.assertFalse(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), -2, "Unknown get code value returned");

    }

    @Test
    public void testGetUserPositive() {
        User user = User.builder()
                .userId(1)
                .firstname("Kamol")
                .lastname("Turaqulov")
                .age(20)
                .username("Kamol2103")
                .createdAt(LocalDateTime.now())
                .password("qwe123")
                .build();

        when(this.userRepository.findByUserIdAndDeletedAtIsNull(any())).thenReturn(of(user));

        when(this.userMapper.toDtoWithCard(any())).thenReturn(
                ResponseUserDto.builder()
                        .userId(1)
                        .firstname("Kamol")
                        .lastname("Turaqulov")
                        .age(20)
                        .username("Kamol2103")
                        .createdAt(LocalDateTime.now())
                        .password("qwe123")
                        .cards(new HashSet<>())
                        .build()
        );

        ResponseDto<ResponseUserDto> response = this.userService.getEntity(any());

        Assertions.assertTrue(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned");

        verify(this.userMapper, times(1)).toDtoWithCard(any());
        verify(this.userRepository, times(1)).findByUserIdAndDeletedAtIsNull(any());

    }

    @Test
    public void testGetUserNegative() {
        when(this.userRepository.findByUserIdAndDeletedAtIsNull(any())).thenReturn(empty());

        ResponseDto<ResponseUserDto> response = this.userService.getEntity(any());

        Assertions.assertFalse(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned");

        verify(this.userRepository, times(1)).findByUserIdAndDeletedAtIsNull(any());

    }

    @Test
    public void testUpdateUserPositive() {

        User user = User.builder()
                .userId(1)
                .firstname("Kamol")
                .lastname("Turaqulov")
                .age(20)
                .username("Kamol2103")
                .createdAt(LocalDateTime.now())
                .password("qwe123")
                .cards(new HashSet<>())
                .build();

        when(this.userValidation.userValidPutMethod(any())).thenReturn(new ArrayList<>());

        when(this.userRepository.findByUserIdAndDeletedAtIsNull(any())).thenReturn(Optional.of(user));

        when(this.userMapper.updateUser(any(), any())).thenReturn(user);

        when(this.userRepository.save(any())).thenReturn(user);

        when(this.userMapper.toDto(any())).thenReturn(ResponseUserDto.builder()
                .userId(1)
                .firstname("Kamol")
                .lastname("Turaqulov")
                .age(20)
                .build());

        ResponseDto<ResponseUserDto> response = this.userService.updateEntity(1, any());

        Assertions.assertTrue(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNotNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), 0, "Unknown get code value returned");

        verify(this.userMapper, times(1)).toDto(any());
        verify(this.userRepository, times(1)).findByUserIdAndDeletedAtIsNull(any());
        verify(this.userMapper,times(1)).updateUser(any(),any());
        verify(this.userRepository, times(1)).save(any());
        verify(this.userValidation,times(1)).userValidPutMethod(any());
    }

    @Test
    public void testUpdateUserNegative() {
        when(this.userRepository.findByUserIdAndDeletedAtIsNull(any())).thenReturn(empty());

        ResponseDto<ResponseUserDto> response = this.userService.updateEntity(1, any());

        Assertions.assertFalse(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned");

        verify(this.userRepository, times(1)).findByUserIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testUpdateUserException() {
        when(this.userRepository.save(any())).thenReturn(RuntimeException.class);
        when(this.userRepository.findByUserIdAndDeletedAtIsNull(any())).thenReturn(empty());

        ResponseDto<ResponseUserDto> response = this.userService.updateEntity(1, any());

        Assertions.assertFalse(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned");

        verify(this.userRepository, times(1)).findByUserIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testUpdateUserValidation() {
        when(this.userValidation.userValidPutMethod(any())).thenReturn(new ArrayList<>());

        ResponseDto<ResponseUserDto> response = this.userService.updateEntity(1, any());

        Assertions.assertFalse(response.isSuccess(), "Unknown get success value returned");
        Assertions.assertNull(response.getErrorList(), "Unknown get error list value returned");
        Assertions.assertNull(response.getContent(), "Unknown get content value returned");
        Assertions.assertEquals(response.getCode(), -1, "Unknown get code value returned");

        verify(this.userValidation, times(1)).userValidPutMethod(any());
    }

    @Test
    public void testDeleteUserPositive() {

    }

    @Test
    public void testDeleteUserNegative() {

    }


}
