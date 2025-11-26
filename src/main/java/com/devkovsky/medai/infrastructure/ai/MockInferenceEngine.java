package com.devkovsky.medai.infrastructure.ai;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 실제 환경에서는 Python 기반 AI 서버 호출 위치.
 * 현재는 프로젝트 데모 / 백엔드 구조를 보여주기 위해 mock 구현으로 동작.
 */
@Component
public class MockInferenceEngine {

    private final Random random = new Random();

    public Result infer(String dicomPath) {
        // 매우 단순한 예시: 경로나 이름에 따라 라벨을 다르게 줌
        String lower = dicomPath.toLowerCase();

        String label;
        if (lower.contains("cxr") || lower.contains("chest")) {
            label = random.nextDouble() > 0.5 ? "Pneumonia" : "Normal";
        } else if (lower.contains("brain") || lower.contains("head")) {
            label = random.nextDouble() > 0.5 ? "Stroke" : "Normal";
        } else {
            label = "Unknown";
        }

        double probability = 0.6 + (0.4 * random.nextDouble()); // 0.6 ~ 1.0 사이

        return new Result(label, probability);
    }

    public record Result(String label, double probability) {
    }
}
