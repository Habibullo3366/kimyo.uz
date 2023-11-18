package com.company.kimyouz.util;

import com.company.kimyouz.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface SimpleRequestCrud<K, RQ, Rs> {
    ResponseEntity<ResponseDto<Rs>> createEntity(RQ entity);

    ResponseEntity<ResponseDto<Rs>> getEntity(K entityId);

    ResponseEntity<ResponseDto<Rs>> updateEntity(K entityId, RQ entity);

    ResponseEntity<ResponseDto<Rs>> deleteEntity(K entityId);
}
