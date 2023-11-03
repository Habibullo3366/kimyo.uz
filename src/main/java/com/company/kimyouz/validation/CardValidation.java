package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.response.ResponseCardDto;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardValidation {
    public List<ErrorDto> cardValid(ResponseCardDto dto) {
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
