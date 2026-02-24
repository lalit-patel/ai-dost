package com.aidost.mock.repository;
import com.aidost.mock.model.MockSession;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface MockSessionRepository extends JpaRepository<MockSession,Long>{ List<MockSession> findAllByUserId(Long userId);}
