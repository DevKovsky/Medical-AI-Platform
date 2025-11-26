# Medical AI Platform 🧬

의료 영상(DICOM)을 기반으로 한 진단 보조 AI 서비스를 위한 **백엔드 플랫폼**입니다.  
현재는 `Study(검사)` 도메인과 기본 REST API를 중심으로 설계되어 있으며,  
향후 AI 추론(Inference), DICOM 연동, 인증/권한 등을 확장해 나가는 것을 목표로 합니다.

---

## ✨ Features

현재 구현된 기능

- **Study 관리**
  - 환자 검사(Study) 등록 API
  - 단일 Study 조회 API
  - Study 목록 조회 API
- **레이어드 아키텍처**
  - api / application / domain 레이어 분리
  - JPA 기반 엔티티 & 리포지토리
- **H2 인메모리 DB**
  - 개발용 DB (재시작 시 초기화)

로드맵(계획)

- [ ] AI 추론(Inference) API 및 Python 서버 연동
- [ ] DICOM 메타데이터 파싱 및 저장 전략
- [ ] Swagger / OpenAPI 기반 API 문서화
- [ ] JWT 기반 인증 및 권한 관리
- [ ] PostgreSQL 기반 운영 환경 구성(docker-compose)

---

## 🏗 Architecture

### Layered Architecture

프로젝트는 **레이어드 아키텍처**를 기반으로 구성되어 있습니다.

- **api**  
  - REST API Controller  
  - Request/Response DTO  
- **application**  
  - 비즈니스 유즈케이스 구현  
  - 트랜잭션 관리  
- **domain**  
  - 엔티티, 도메인 규칙  
  - 리포지토리 인터페이스  
- **infrastructure** (예정)  
  - 외부 연동 (AI 서버, Storage, PACS 등)

### Directory Structure

```text
src/
 └ main/
    ├ java/
    │  └ com/devkovsky/medai/
    │      ├ api/
    │      │  ├ controller/
    │      │  │   └ StudyController.java
    │      │  └ dto/
    │      │      └ StudyDtos.java
    │      ├ application/
    │      │  └ StudyService.java
    │      ├ domain/
    │      │  └ study/
    │      │      ├ Study.java
    │      │      └ StudyRepository.java
    │      └ MedicalAiPlatformApplication.java
    └ resources/
       └ application.yml
```

---

## 🧰 Tech Stack

- **Language**: Java 17  
- **Framework**: Spring Boot 3.x  
- **Persistence**: Spring Data JPA  
- **Database**: H2 (In-memory)  
- **Build Tool**: Gradle  
- **Other**: Jakarta Validation, REST API

---

## 🚀 Getting Started

### Requirements
- JDK 17+
- Gradle (Wrapper included)
- Git

### Clone

```bash
git clone https://github.com/DevKovsky/Medical-AI-Platform.git
cd Medical-AI-Platform
```

### Run (Dev / H2)

```bash
./gradlew bootRun
```

서버 주소:

```
http://localhost:8080
```

### H2 Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:medai`
- USER: `sa`
- PASSWORD: (빈 값)

---

## 📡 API Overview

### 1) Create Study

**POST** `/api/v1/studies`

#### Request

```json
{
  "patientId": "P0001",
  "description": "Chest X-Ray for pneumonia",
  "dicomPath": "/data/dicom/001"
}
```

#### Response

```json
{
  "id": 1,
  "patientId": "P0001",
  "description": "Chest X-Ray for pneumonia",
  "dicomPath": "/data/dicom/001"
}
```

---

### 2) Get Study

**GET** `/api/v1/studies/{id}`

#### Example

```json
{
  "id": 1,
  "patientId": "P0001",
  "description": "Chest X-Ray for pneumonia",
  "dicomPath": "/data/dicom/001"
}
```

---

### 3) List Studies

**GET** `/api/v1/studies`

#### Example

```json
[
  {
    "id": 1,
    "patientId": "P0001",
    "description": "Chest X-Ray for pneumonia",
    "dicomPath": "/data/dicom/001"
  },
  {
    "id": 2,
    "patientId": "P0002",
    "description": "Brain MRI",
    "dicomPath": "/data/dicom/002"
  }
]
```

---

## 🧪 Local Testing (curl)

```bash
# Create Study
curl.exe -X POST "http://localhost:8080/api/v1/studies" ^
  -H "Content-Type: application/json" ^
  -d "{ \"patientId\": \"P0001\", \"description\": \"Chest X-Ray for pneumonia\", \"dicomPath\": \"/data/dicom/001\" }"

# Get Study
curl.exe "http://localhost:8080/api/v1/studies/1"

# List Studies
curl.exe "http://localhost:8080/api/v1/studies"
```

---

## 🧾 Domain Model

### Study

| Field        | Type   | Description              |
|--------------|--------|--------------------------|
| id           | Long   | PK                       |
| patientId    | String | 환자 ID                  |
| description  | String | 검사 설명                |
| dicomPath    | String | DICOM 파일 경로 또는 키  |

---

## 🗺 Roadmap

- [ ] **Inference API**  
  AI 모델(Python) 호출 → 분석 결과 저장  
- [ ] **DICOM 처리**  
  메타데이터 파싱, PACS 연동  
- [ ] **Swagger / OpenAPI**  
  문서 자동화 (`/swagger-ui.html`)  
- [ ] **JWT 인증 / 권한 관리**  
- [ ] **PostgreSQL 운영 환경**  
  docker-compose 기반 DB 구성  

---

## 🤝 Contributing

이 프로젝트는 의료 AI 백엔드 구조를 연구하고 발전시키기 위해 만들어졌습니다.  
이슈 제안 및 PR은 언제든지 환영합니다!

---

## 📄 License

추후 MIT / Apache 2.0 라이선스를 적용할 수 있습니다.
