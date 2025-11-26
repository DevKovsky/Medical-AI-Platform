package com.devkovsky.medai.application;

import com.devkovsky.medai.api.dto.InferenceDtos;
import com.devkovsky.medai.domain.inference.InferenceResult;
import com.devkovsky.medai.domain.inference.InferenceResultRepository;
import com.devkovsky.medai.domain.inference.InferenceStatus;
import com.devkovsky.medai.domain.study.Study;
import com.devkovsky.medai.domain.study.StudyRepository;
import com.devkovsky.medai.infrastructure.ai.MockInferenceEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InferenceService {

    private final StudyRepository studyRepository;
    private final InferenceResultRepository inferenceResultRepository;
    private final MockInferenceEngine mockInferenceEngine;

    public InferenceService(StudyRepository studyRepository,
                            InferenceResultRepository inferenceResultRepository,
                            MockInferenceEngine mockInferenceEngine) {
        this.studyRepository = studyRepository;
        this.inferenceResultRepository = inferenceResultRepository;
        this.mockInferenceEngine = mockInferenceEngine;
    }

    /**
     * 특정 Study에 대해 AI 추론을 수행하고 결과를 저장한다.
     */
    public InferenceResult runInference(Long studyId) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new IllegalArgumentException("Study not found: " + studyId));

        // 실제 환경에서는 여기서 Python 서버 호출
        MockInferenceEngine.Result result = mockInferenceEngine.infer(study.getDicomPath());

        InferenceResult inferenceResult = new InferenceResult(
                study,
                result.label(),
                result.probability(),
                InferenceStatus.DONE
        );

        return inferenceResultRepository.save(inferenceResult);
    }

    @Transactional(readOnly = true)
    public InferenceResult getInference(Long id) {
        return inferenceResultRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inference result not found: " + id));
    }

    @Transactional(readOnly = true)
    public InferenceDtos.Response getInferenceResponse(Long id) {
        return InferenceDtos.Response.from(getInference(id));
    }
}
