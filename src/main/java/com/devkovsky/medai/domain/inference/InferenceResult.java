package com.devkovsky.medai.domain.inference;

import com.devkovsky.medai.domain.study.Study;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inference_results")
public class InferenceResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 Study에 대한 결과인지
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "study_id")
    private Study study;

    // AI가 예측한 라벨 (예: Pneumonia, Normal 등)
    @Column(nullable = false)
    private String label;

    // 확률 (0.0 ~ 1.0)
    @Column(nullable = false)
    private double probability;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InferenceStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected InferenceResult() {
    }

    public InferenceResult(Study study, String label, double probability, InferenceStatus status) {
        this.study = study;
        this.label = label;
        this.probability = probability;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Study getStudy() {
        return study;
    }

    public String getLabel() {
        return label;
    }

    public double getProbability() {
        return probability;
    }

    public InferenceStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
