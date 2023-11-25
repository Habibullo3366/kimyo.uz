package com.company.kimyouz.repository;

import com.company.kimyouz.entity.UserAccessSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessSessionRepository extends CrudRepository<UserAccessSession, String> {

}
