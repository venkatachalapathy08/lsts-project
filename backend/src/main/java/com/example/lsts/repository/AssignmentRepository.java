package com.example.lsts.repository;

import com.example.lsts.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity , Long> {
}
