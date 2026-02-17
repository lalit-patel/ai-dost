package com.aidost.profile.service;
import com.aidost.profile.model.UserProfile;import com.aidost.profile.repository.UserProfileRepository;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
 private final UserProfileRepository repo;
 public UserProfile getMe(Long userId){ return repo.findByUserId(userId).orElse(UserProfile.builder().userId(userId).build()); }
 public UserProfile updateMe(Long userId, UserProfile profile){ profile.setUserId(userId); return repo.save(profile);} }
