package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestCardDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardValidation {
    public List<ErrorDto> cardValid(RequestCardDto dto) {
        List<ErrorDto> errorsList = new ArrayList<>();
        if (StringUtils.isBlank(dto.getCardName())){
            errorsList.add(new ErrorDto("cardName", "CardName cannot be null or empty."));
        }
        if (StringUtils.isBlank(dto.getCardFullName())){
            errorsList.add(new ErrorDto("cardFullName", "CardFullName cannot be null or empty."));
        }
        if (StringUtils.isBlank(dto.getCardCode())) {
            errorsList.add(new ErrorDto("cardCode", "CardCode cannot be null or empty."));
        }

        return errorsList;
    }
}
