package com.company.kimyouz.entity;

//import com.company.kimyouz.util.BuildTime;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
//@EntityListeners(value = BuildTime.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

//    @Column(nullable = false)
    private String firstname;

//    @Column(nullable = false)
    private String lastname;

    private String username;
    private String password;

//    @Column(nullable = false)
    private Integer age;

    private boolean enabled;

    @OneToMany(mappedBy = "username",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Authorities> authorities;

    @OneToMany(mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Card> cards;

    @OneToMany(mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "userId",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Orders> orders;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
