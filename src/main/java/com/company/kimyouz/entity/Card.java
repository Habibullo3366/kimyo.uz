package com.company.kimyouz.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
//@EntityListeners(value= BuildTime.class)
public class Card {
     @Id
     @GeneratedValue(strategy =GenerationType.IDENTITY)
     private Integer cardId;

//     @Column(name = "cardName")

     @Column(nullable = false)
     private String cardName;

     @Column(nullable = false)
     private String cardFullName;

     @Column(name = "user_id")
     private Integer userId;

     @Column(nullable = false, unique = true)
     private String cardCode;

     @ManyToOne
     @JoinColumn(name = "user_id", insertable = false, updatable = false)
     private User users;

     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     private LocalDateTime deletedAt;

//     @Embedded
//     private BuildTime buildTime;
}
