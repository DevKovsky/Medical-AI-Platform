package com.devkovsky.medai.api.dto;

import com.devkovsky.medai.domain.study.Study;
import jakarta.validation.constraints.NotBlank;

public class StudyDtos {

    public record CreateRequest(
            @NotBlank String patientId,
            @NotBlank String description,
            @NotBlank String dicomPath
    ) {
    }

    public record Response(
            Long id,
            String patientId,
            String description,
            String dicomPath
    ) {
        public static Response from(Study study) {
            return new Response(
                    study.getId(),
                    study.getPatientId(),
                    study.getDescription(),
                    study.getDicomPath()
            );
        }
    }
}
