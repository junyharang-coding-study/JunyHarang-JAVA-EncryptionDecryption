package com.junyharang.endecrypttest.repository;

import com.junyharang.endecrypttest.model.entity.TestBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<TestBoard, Long> {}
