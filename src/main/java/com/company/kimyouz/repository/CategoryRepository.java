package com.company.kimyouz.repository;

import com.company.kimyouz.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Integer categoryId);

}
