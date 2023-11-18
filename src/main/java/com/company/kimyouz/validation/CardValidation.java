package com.company.kimyouz.validation;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.request.RequestCardDto;
<<<<<<< HEAD
import io.micrometer.common.util.StringUtils;
=======
import com.company.kimyouz.dto.response.ResponseCardDto;
import com.company.kimyouz.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 53a017f012e6af853c75eaef64be739d1b0dd86a
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardValidation {
<<<<<<< HEAD
=======

    @Autowired
    private UserRepository userRepository;

>>>>>>> 53a017f012e6af853c75eaef64be739d1b0dd86a
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
        if (this.userRepository.findByUserIdAndDeletedAtIsNull(dto.getUserId()).isEmpty()) {
            errorsList.add(new ErrorDto("userId", String.format("User with %d:id is not found!", dto.getUserId())));
        }

        return errorsList;
    }
}
