package com.company.kimyouz.repository;

import com.company.kimyouz.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {

    Optional<Authorities> findByUsernameAndAuthority(String username, String auth);

}
