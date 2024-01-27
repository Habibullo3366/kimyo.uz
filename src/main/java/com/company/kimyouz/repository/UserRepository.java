package com.company.kimyouz.repository;

import com.company.kimyouz.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByDeletedAtIsNull();

    Optional<User> findByUserIdAndDeletedAtIsNull(Integer userId);

    Optional<User> findByUsernameAndDeletedAtIsNullAndEnabledIsTrue(String username);

    Optional<User> findByUsernameAndDeletedAtIsNull(String username);


    Boolean existsByUsernameAndDeletedAtIsNull(String username);

    Boolean existsByUserIdAndDeletedAtIsNull(Integer userId);



}
