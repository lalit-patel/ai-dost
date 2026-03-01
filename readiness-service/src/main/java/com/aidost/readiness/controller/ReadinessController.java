package com.aidost.readiness.controller;
import com.aidost.readiness.dto.ReadinessResponse;import com.aidost.readiness.service.ReadinessService;import lombok.RequiredArgsConstructor;import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController @RequestMapping("/readiness") @RequiredArgsConstructor public class ReadinessController { private final ReadinessService service; @GetMapping("/me") public ReadinessResponse me(){ return service.getScore(1L);} @GetMapping("/candidate/{id}") public ReadinessResponse candidate(@PathVariable Long id){ return service.getScore(id);} }
