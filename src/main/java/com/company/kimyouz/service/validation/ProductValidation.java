package com.company.kimyouz.service.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestProductDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidation {
    public List<ErrorDto> productValid(RequestProductDto dto) {
        List<ErrorDto> errorList=new ArrayList<>();
        if (StringUtils.isBlank(dto.getProdName())){
            errorList.add(new ErrorDto("prodName","ProdName cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getProdColor())){
            errorList.add(new ErrorDto("prodColor","ProdColor cannot be null or empty"));
        }
        if (StringUtils.isBlank(dto.getProdType())){
            errorList.add(new ErrorDto("prodType","ProdType cannot be null or empty"));
        }
        if (StringUtils.isBlank(String.valueOf(dto.getProdAmount()))){
            errorList.add(new ErrorDto("prodAmount","ProdAmount cannot be null or empty"));
        }
        if (StringUtils.isBlank(String.valueOf(dto.getProdPrice()))){
            errorList.add(new ErrorDto("prodPrice","ProdPrice cannot be null or empty"));
        }
        return errorList;
    }
}
