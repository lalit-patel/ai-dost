package com.aidost.resume.controller;
import com.aidost.resume.model.Resume;import com.aidost.resume.repository.ResumeRepository;import lombok.RequiredArgsConstructor;import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController @RequestMapping("/resume/me") @RequiredArgsConstructor public class ResumeController { private final ResumeRepository repo; @GetMapping public Resume get(){ return repo.findByUserId(1L).orElse(Resume.builder().userId(1L).build()); } @PutMapping public Resume put(@RequestBody Resume r){ r.setUserId(1L); return repo.save(r);} @PostMapping("/generate") public String generate(){ return "ATS/PDF generation stub";} }
