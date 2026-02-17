package com.aidost.mock.service;
import com.aidost.mock.model.MockSession;import java.util.List;
public interface MockService { MockSession create(Long userId, MockSession request); List<MockSession> list(Long userId); MockSession get(Long userId, Long id); }
