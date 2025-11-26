package com.devkovsky.medai.application;

import com.devkovsky.medai.api.dto.StudyDtos;
import com.devkovsky.medai.domain.study.Study;
import com.devkovsky.medai.domain.study.StudyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudyService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public Study createStudy(StudyDtos.CreateRequest request) {
        Study study = new Study(
                request.patientId(),
                request.description(),
                request.dicomPath()
        );
        return studyRepository.save(study);
    }

    @Transactional(readOnly = true)
    public Study getStudy(Long id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Study not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Study> getStudies() {
        return studyRepository.findAll();
    }
}
