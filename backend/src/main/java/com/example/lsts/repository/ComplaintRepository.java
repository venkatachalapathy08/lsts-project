package com.example.lsts.repository;

import com.example.lsts.entity.ComplaintEntity;
import com.example.lsts.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<ComplaintEntity , Long> {
    List<ComplaintEntity> findByUser(UserEntity user);
}
