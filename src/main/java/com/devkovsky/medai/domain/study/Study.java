package com.devkovsky.medai.domain.study;

import jakarta.persistence.*;

@Entity
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String dicomPath;

    protected Study() {
    }

    public Study(String patientId, String description, String dicomPath) {
        this.patientId = patientId;
        this.description = description;
        this.dicomPath = dicomPath;
    }

    public Long getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDescription() {
        return description;
    }

    public String getDicomPath() {
        return dicomPath;
    }
}
