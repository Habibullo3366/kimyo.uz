package com.company.kimyouz.service.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestPaymentDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentValidation {
    public List<ErrorDto> paymentValid(RequestPaymentDto dto) {
        List<ErrorDto> errorsList = new ArrayList<>();
        if (StringUtils.isBlank(String.valueOf(dto.getTotalPrice()))) {
            errorsList.add(new ErrorDto("totalPrice", "TotalPrice cannot be null or empty."));
        }
        if (StringUtils.isBlank(String.valueOf(dto.getPaymentDate()))) {
            errorsList.add(new ErrorDto("paymentDate", "PaymentDate cannot be null or empty."));
        }

        return errorsList;
    }
}
