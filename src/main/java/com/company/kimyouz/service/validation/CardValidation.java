package com.company.kimyouz.service.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestCardDto;
import com.company.kimyouz.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardValidation {

    private final UserRepository userRepository;


    public List<ErrorDto> cardValid(RequestCardDto dto) {
        List<ErrorDto> errorsList = new ArrayList<>();

        if (this.userRepository.findByUserIdAndDeletedAtIsNullOrderByCardsAsc(dto.getUserId()).isEmpty()) {
            errorsList.add(new ErrorDto(dto.getUserId().toString(), String.format("User with %d :: id is not found!", dto.getUserId())));
        }

        return errorsList;
    }


}
