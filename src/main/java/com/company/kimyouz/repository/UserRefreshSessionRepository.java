package com.company.kimyouz.repository;

import com.company.kimyouz.entity.UserRefreshSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshSessionRepository extends CrudRepository<UserRefreshSession, String> {
}
