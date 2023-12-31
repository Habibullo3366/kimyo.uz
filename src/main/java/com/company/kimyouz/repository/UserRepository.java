package com.company.kimyouz.repository;

import com.company.kimyouz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByDeletedAtIsNull();
    Optional<User> findByUserIdAndDeletedAtIsNull(Integer userId);
    Optional<User> findByUserIdAndDeletedAtIsNullOrderByCardsAsc(Integer userId);

    boolean findAllByUserIdAndDeletedAtIsNull();


}
