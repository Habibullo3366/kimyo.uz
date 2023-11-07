package com.company.kimyouz.repository;

import com.company.kimyouz.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select * " +
            "from products as p " +
            "where p.prodId = :id " +
            "and deleted_at is null",
            nativeQuery = true)
    Optional<Product> findByProductId(@Param(value = "id") Integer prodId);

    @Query(
            value = "select p from Product as p order by p.prodId",
            countQuery = "select count(p.prodId) from Product as p",
            nativeQuery = true
    )
    Page<Product> findAllByPage(Pageable pageable);


    Optional<Product> findByProdIdAndDeletedAtIsNull(Integer prodId);


    @Query(
            value = "select p " +
                    "from Product as p \n" +
                    "where p.prodId = coalesce(:id, p.prodId) \n" +
                    "  and p.prodName = coalesce(:name, p.prodName) \n" +
                    "  and p.prodColor = coalesce(:color, p.prodColor) \n" +
                    "  and p.prodType = coalesce(:type, p.prodType) \n" +
                    "  and p.prodAmount >= coalesce(:amount, p.prodAmount) \n" +
                    "  and p.prodPrice >= coalesce(:price, p.prodPrice) ",
            countQuery = "select count(p.prodId) from Product as p"
    )
    Page<Product> searchAllProductWithMoreParams(
            @Param(value = "id") Integer prodId,
            @Param(value = "name") String prodName,
            @Param(value = "color") String prodColor,
            @Param(value = "type") String prodType,
            @Param(value = "price") Double prodPrice,
            @Param(value = "amount") Integer prodAmount,
            Pageable pageable


    );

}
