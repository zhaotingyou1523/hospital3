package main.Java.com.hospital.repository;

import main.Java.com.hospital.domain.Doctor;
import main.Java.com.hospital.domain.Gender;
import main.Java.com.hospital.domain.Department;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
// 의사 데이터베이스
public class DoctorRepository {
    private long id = 0L;
    private static Map<Long, Doctor> doctorMap = new HashMap<>();

    // 의사 추가
    public synchronized Doctor insert(String name, Gender gender, LocalDate birthYear, LocalDate jointime, String professional, Department department){
        long doctorId = ++id;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        doctor.setDepartment(department);
        doctor.setName(name);
        doctor.setGender(gender);
        doctor.setProfessional(professional);
        doctor.setBirthYear(birthYear);
        doctor.setJoinDate(jointime);
        doctorMap.put(doctor.getId(),doctor);
        return doctor;

    }

    // 의사 데이터 수정
    public synchronized Doctor update(long id,Doctor doctor){
        Doctor doc = doctorMap.get(id);
        doc.setName(doctor.getName());
        doc.setGender(doctor.getGender());
        doc.setBirthYear(doctor.getBirthYear());
        return doctor;
    }

    // 의사 삭제
    public synchronized Doctor delete(long id){
        return doctorMap.remove(id);
    }

    //의사 수가 비어 있는지 확인합니다.
    public boolean check(){
    return !doctorMap.isEmpty();
}

    //ID로 의사 정보를 조회
    public Doctor searchById(long doctorId){
        return doctorMap.values().stream().filter(doctor -> doctor.getId() == doctorId).findFirst().orElse(null);
    }

    //의사명으로 의사 정보 조회
    public List<Doctor> searchByDoctorName(String name) {
        return doctorMap.values().stream().filter(doctor -> name.equals(doctor.getName())).collect(Collectors.toList());
    }
}
