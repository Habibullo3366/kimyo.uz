package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.response.ResponseBasketDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BasketValidation {
    public List<ErrorDto> basketValid(ResponseBasketDto dto){
        List<ErrorDto> errorList = new ArrayList<>();
        if (StringUtils.isBlank(String.valueOf(dto.getTotalPrice())))
            errorList.add(new ErrorDto("totalPrice", "TotalPrice cannot be null or empty."));




        return errorList;
    }

}
