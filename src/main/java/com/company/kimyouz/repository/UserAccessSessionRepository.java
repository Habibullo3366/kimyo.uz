package com.company.kimyouz.repository;

import com.company.kimyouz.dto.response.ResponseUserDto;
import com.company.kimyouz.entity.UserAccessSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccessSessionRepository extends CrudRepository<UserAccessSession, String> {

    Optional<UserAccessSession> findUserAccessSessionByUserDto(ResponseUserDto dto);
}
