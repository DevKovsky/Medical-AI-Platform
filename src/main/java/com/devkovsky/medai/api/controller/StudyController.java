package com.devkovsky.medai.api.controller;

import com.devkovsky.medai.api.dto.StudyDtos;
import com.devkovsky.medai.application.StudyService;
import com.devkovsky.medai.domain.study.Study;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/studies")
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @PostMapping
    public ResponseEntity<StudyDtos.Response> createStudy(@Valid @RequestBody StudyDtos.CreateRequest request) {
        Study study = studyService.createStudy(request);
        StudyDtos.Response response = StudyDtos.Response.from(study);
        return ResponseEntity
                .created(URI.create("/api/v1/studies/" + study.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyDtos.Response> getStudy(@PathVariable Long id) {
        Study study = studyService.getStudy(id);
        return ResponseEntity.ok(StudyDtos.Response.from(study));
    }

    @GetMapping
    public ResponseEntity<List<StudyDtos.Response>> getStudies() {
        List<StudyDtos.Response> responses = studyService.getStudies()
                .stream()
                .map(StudyDtos.Response::from)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
