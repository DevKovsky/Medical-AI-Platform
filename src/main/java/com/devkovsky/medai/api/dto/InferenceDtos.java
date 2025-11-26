package com.devkovsky.medai.api.dto;

import com.devkovsky.medai.domain.inference.InferenceResult;
import com.devkovsky.medai.domain.inference.InferenceStatus;

public class InferenceDtos {

    public record Response(
            Long id,
            Long studyId,
            String label,
            double probability,
            InferenceStatus status,
            String createdAt
    ) {
        public static Response from(InferenceResult result) {
            return new Response(
                    result.getId(),
                    result.getStudy().getId(),
                    result.getLabel(),
                    result.getProbability(),
                    result.getStatus(),
                    result.getCreatedAt().toString()
            );
        }
    }
}
