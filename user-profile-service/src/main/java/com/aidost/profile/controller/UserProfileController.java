package com.aidost.profile.controller;
import com.aidost.profile.model.UserProfile;import com.aidost.profile.service.UserProfileService;import lombok.RequiredArgsConstructor;import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController @RequestMapping("/profile") @RequiredArgsConstructor
public class UserProfileController {
 private final UserProfileService service;
 @GetMapping("/me") public UserProfile me(){ return service.getMe(1L);} 
 @PutMapping("/me") public UserProfile update(@RequestBody UserProfile profile){ return service.updateMe(1L, profile);} 
 @PostMapping("/linkedin/import") public String importLinkedin(){ return "LinkedIn import placeholder"; }
}
