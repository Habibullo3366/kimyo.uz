package com.company.kimyouz.mapper;

import com.company.kimyo.uz.dto.FileDto;
import com.company.kimyo.uz.model.FileModel;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {

    public FileDto toDto(FileModel file) {
        return FileDto.builder()
                .ext(file.getExt())
                .path(file.getPath())
                .fileId(file.getFileId())
                .fileName(file.getFileName())
                .contentType(file.getContentType())
                .createdAt(file.getCreatedAt())
                .deletedAt(file.getDeletedAt())
                .build();
    }
}
