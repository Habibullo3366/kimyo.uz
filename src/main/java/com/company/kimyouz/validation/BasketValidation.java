package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestBasketDto;
<<<<<<< HEAD
=======
import com.company.kimyouz.dto.response.ResponseBasketDto;
>>>>>>> 53a017f012e6af853c75eaef64be739d1b0dd86a
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
