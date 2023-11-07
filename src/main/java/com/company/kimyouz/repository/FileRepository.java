package com.company.kimyouz.repository;

import com.company.kimyouz.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Integer> {

    Optional<FileModel> findByFileIdAndDeletedAtIsNull(Integer fileId);


}
