package com.devkovsky.medai.api.controller;

import com.devkovsky.medai.api.dto.InferenceDtos;
import com.devkovsky.medai.application.InferenceService;
import com.devkovsky.medai.domain.inference.InferenceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/inference")
public class InferenceController {

    private final InferenceService inferenceService;

    public InferenceController(InferenceService inferenceService) {
        this.inferenceService = inferenceService;
    }

    /**
     * 특정 Study에 대해 AI 추론을 요청한다.
     * 예) POST /api/v1/inference/1
     */
    @PostMapping("/{studyId}")
    public ResponseEntity<InferenceDtos.Response> runInference(@PathVariable Long studyId) {
        InferenceResult result = inferenceService.runInference(studyId);
        InferenceDtos.Response response = InferenceDtos.Response.from(result);
        return ResponseEntity
                .created(URI.create("/api/v1/inference/" + result.getId()))
                .body(response);
    }

    /**
     * 추론 결과 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<InferenceDtos.Response> getInference(@PathVariable Long id) {
        return ResponseEntity.ok(inferenceService.getInferenceResponse(id));
    }
}
