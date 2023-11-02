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
@Table(name = "files")
public class FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;

    private String fileName;
    private String ext;
    private String path;
    private String contentType;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

}
