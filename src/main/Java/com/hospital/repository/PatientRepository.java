package main.Java.com.hospital.repository;



import main.Java.com.hospital.domain.Gender;
import main.Java.com.hospital.domain.Patient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// 환자 데이터베이스
public class PatientRepository {
    private Integer id = 0;
    private static Map<Integer, Patient> patientMap = new HashMap<>();

    // 환자 추가
    public synchronized Patient insert(String name, Gender gender, String phoneNumber, String description, LocalDate birthYear){
        Integer patientId = ++id;
        Patient patient = new Patient();
        patient.setName(name);
        patient.setDescription(description);
        patient.setId(patientId);
        patient.setGender(gender);
        patient.setBirthYear(birthYear);
        patient.setPhoneNumber(phoneNumber);
        return patientMap.put(patient.getId(),patient);
    }

    // 환자 정보 수정
    public synchronized Patient update(Integer id,Patient patient){
        Patient ptt = patientMap.get(id);
        ptt.setName(patient.getName());
        ptt.setDescription(patient.getDescription());
        ptt.setGender(patient.getGender());
        ptt.setBirthYear(patient.getBirthYear());
        ptt.setPhoneNumber(patient.getPhoneNumber());
        return ptt;
    }

    // 환자 삭제
    public synchronized Patient delete(Integer id){
        return patientMap.remove(id);
    }

    //환자 수가 비어 있는지 확인합니다.
    public boolean check(){
        return !patientMap.isEmpty();
    }

    //ID로 환자 정보를 조회
    public Patient searchById(Integer patientId){
        return patientMap.values().stream().filter(patient -> Objects.equals(patient.getId(), patientId)).findFirst().orElse(null);
    }

    //환자명으로 환자 정보 조회
    public List<Patient> searchByPatientName(String name) {
        return patientMap.values().stream().filter(patient -> name.equals(patient.getName())).collect(Collectors.toList());
    }
}
