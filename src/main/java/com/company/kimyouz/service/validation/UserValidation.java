package com.company.kimyouz.service.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestUserDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {
    public List<ErrorDto> userValid(RequestUserDto dto) {
        List<ErrorDto> errorList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getFirstname())) {
            errorList.add(new ErrorDto("firstname", "Firstname cannot be null or empty."));
        }
        if (StringUtils.isBlank(dto.getLastname())) {
            errorList.add(new ErrorDto("lastname", "Lastname cannot be null or empty."));
        }
        if (StringUtils.isBlank(dto.getUsername())) {
            errorList.add(new ErrorDto("Username", "Email cannot be null or empty."));
        }
        if (StringUtils.isBlank(dto.getPassword())) {
            errorList.add(new ErrorDto("password", "Lastname cannot be null or empty."));
        }
        if (dto.getAge() == null || dto.getAge() < 0) {
            errorList.add(new ErrorDto("age", "Age cannot be null or empty."));
        }
        //...
        return errorList;
    }
}
