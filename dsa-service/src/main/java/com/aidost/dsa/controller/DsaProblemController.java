package com.aidost.dsa.controller;

import com.aidost.dsa.dto.DsaProblemDto;
import com.aidost.dsa.dto.UpdateProblemNotesRequest;
import com.aidost.dsa.dto.UpdateProblemStatusRequest;
import com.aidost.dsa.model.Problem;
import com.aidost.dsa.service.DsaProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/dsa")
public class DsaProblemController {
  private final DsaProblemService dsaProblemService;

  @GetMapping("/problems")
  public List<Problem> getMasterProblems() {
    return dsaProblemService.getAllProblems();
  }

  @GetMapping("/me/problems")
  public List<DsaProblemDto> getMyProblems() {
    return dsaProblemService.getProblemsForUser(1L);
  }

  @PutMapping("/me/problems/{problemId}/status")
  public DsaProblemDto updateStatus(@PathVariable Long problemId, @Valid @RequestBody UpdateProblemStatusRequest request) {
    return dsaProblemService.updateStatus(1L, problemId, request.getStatus());
  }

  @PutMapping("/me/problems/{problemId}/notes")
  public DsaProblemDto updateNotes(@PathVariable Long problemId, @Valid @RequestBody UpdateProblemNotesRequest request) {
    return dsaProblemService.updateNotes(1L, problemId, request.getNotes());
  }
}
