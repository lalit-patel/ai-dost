package com.aidost.readiness.service;
import com.aidost.readiness.dto.ReadinessResponse;import org.springframework.stereotype.Service;
@Service public class RuleBasedReadinessService implements ReadinessService { public ReadinessResponse getScore(Long id){ int d=70,c=65,p=60,m=72; return ReadinessResponse.builder().userId(id).dsa(d).coreTech(c).projects(p).communication(m).total((d+c+p+m)/4).build(); }}
