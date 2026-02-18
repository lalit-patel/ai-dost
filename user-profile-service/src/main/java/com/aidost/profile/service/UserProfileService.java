package com.aidost.profile.service;
import com.aidost.profile.model.UserProfile;
public interface UserProfileService { UserProfile getMe(Long userId); UserProfile updateMe(Long userId, UserProfile profile); }
