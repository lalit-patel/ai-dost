package com.aidost.resume.repository;
import com.aidost.resume.model.Resume;import org.springframework.data.jpa.repository.JpaRepository;import java.util.Optional;
public interface ResumeRepository extends JpaRepository<Resume,Long>{ Optional<Resume> findByUserId(Long userId);}
