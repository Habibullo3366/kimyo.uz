package com.company.kimyouz.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "authorities")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authoritiesId;

    private Integer usersId;

    private String authority;

    private String username;
}
