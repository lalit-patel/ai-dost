package com.aidost.profile.repository;
import com.aidost.profile.model.UserProfile;import org.springframework.data.jpa.repository.JpaRepository;import java.util.Optional;
public interface UserProfileRepository extends JpaRepository<UserProfile,Long>{ Optional<UserProfile> findByUserId(Long userId);}
