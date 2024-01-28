package com.company.kimyouz.repository;

//import com.Jwts.JwtsFilter.model.Authorities;
import com.company.kimyouz.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
}
