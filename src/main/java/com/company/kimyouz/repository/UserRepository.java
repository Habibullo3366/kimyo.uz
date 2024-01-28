package com.company.kimyouz.repository;

import com.company.kimyouz.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    List<Users> findAllByDeletedAtIsNull();

    Optional<Users> findByUserIdAndDeletedAtIsNull(Integer userId);

    Optional<Users> findByUserIdAndDeletedAtIsNullOrderByCardsAsc(Integer userId);

    Optional<Users> findByUsernameAndDeletedAtIsNull(String Username);


}
