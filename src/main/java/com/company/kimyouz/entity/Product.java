package com.company.kimyouz.entity;


//import com.company.kimyouz.util.BuildTime;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EntityListeners(value = BuildTime.class)
@Table(name = "product",
        indexes = @Index(name = "prod_id_ix", columnList = "prod_id"),
        uniqueConstraints = @UniqueConstraint(name = "prod_name_uq_ix", columnNames = "prod_id")
)
@NamedQuery(
        name = "findByProductId",
        query = "select p from Product as p where p.prodId = :id and deletedAt is null"
)
public class Product {

    // lower priority

    @Id
    @Column(name = "prod_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prodId;

    @Column(nullable = false)
    private String prodName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String stock;

    private String prodColor;
    private Double prodPrice;
    private Integer prodAmount;
    private String prodType;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "basket_id")
    private Integer basketId;
    @Column(name = "order_item_id")
    private Integer orderItemId;


    @ManyToOne
    @JoinColumn(name = "basket_id", insertable = false, updatable = false)
    private Basket basket;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrdersItem> ordersItems;

    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;

}
