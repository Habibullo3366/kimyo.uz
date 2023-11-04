package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestOrdersItemDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersItemValidation {
    public List<ErrorDto> ordersItemValid(RequestOrdersItemDto dto) {
        List<ErrorDto> errorsList = new ArrayList<>();
        if (StringUtils.isBlank(String.valueOf(dto.getQuantity()))) {
            errorsList.add(new ErrorDto("quantity", "Quantity cannot be null or empty."));
        }
        if (StringUtils.isBlank(String.valueOf(dto.getTotalPrice()))) {
            errorsList.add(new ErrorDto("totalPrice", "TotalPrice cannot be null or empty."));
        }
        return errorsList;
    }

}
