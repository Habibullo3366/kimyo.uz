package com.company.kimyouz.unit.user;


import com.company.kimyouz.repository.UserRepository;
import com.company.kimyouz.service.UserService;
import com.company.kimyouz.service.mapper.UserMapper;
import com.company.kimyouz.service.validation.UserValidation;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class TestUserService {
    private UserService userService;
    private UserValidation userValidation;
    private UserRepository userRepository;
    private UserMapper userMapper;

}
