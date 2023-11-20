package com.company.kimyouz.service.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestBasketDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BasketValidation {
    public List<ErrorDto> basketValid(RequestBasketDto dto){
        List<ErrorDto> errorList = new ArrayList<>();
        if (StringUtils.isBlank(String.valueOf(dto.getTotalPrice())))
            errorList.add(new ErrorDto("totalPrice", "TotalPrice cannot be null or empty."));
        return errorList;
    }

}
