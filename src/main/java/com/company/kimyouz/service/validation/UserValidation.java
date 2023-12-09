package com.company.kimyouz.service.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestUserDto;
import com.company.kimyouz.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {

    private final UserRepository userRepository;


    public List<ErrorDto> userValid(RequestUserDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (this.userRepository.existsByUsernameAndDeletedAtIsNull(dto.getUsername())) {
            errorList.add(new ErrorDto("username", String.format("THis %s username already exists!", dto.getUsername())));
        }
        return errorList;
    }

    public List<ErrorDto> userValidPutMethod(RequestUserDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (dto.getUsername() != null){
            if (this.userRepository.existsByUsernameAndDeletedAtIsNull(dto.getUsername())) {
                errorList.add(new ErrorDto("username", String.format("THis %s username already exists!", dto.getUsername())));
            }
        }
        return errorList;
    }
}
