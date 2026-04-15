package com.example.lsts.repository;

import com.example.lsts.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity , Long> {
}
