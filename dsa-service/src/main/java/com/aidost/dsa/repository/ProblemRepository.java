package com.aidost.dsa.repository;

import com.aidost.dsa.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> { }
