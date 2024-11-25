package main.Java.com.hospital.service;



import main.Java.com.hospital.domain.Department;
import main.Java.com.hospital.domain.Doctor;
import main.Java.com.hospital.domain.Gender;
import main.Java.com.hospital.repository.DepartmentRepository;
import main.Java.com.hospital.repository.DoctorRepository;

import java.time.LocalDate;
import java.util.List;

public class DoctorService {
    private DepartmentRepository departmentRepository = new DepartmentRepository();
    private DoctorRepository repository = new DoctorRepository();

    public DoctorService(DepartmentRepository departmentRepository, DoctorRepository repository) {
        this.departmentRepository = departmentRepository;
        this.repository = repository;
    }

    //添加医生
    public Doctor addDoctor(String name, Gender gender, LocalDate birthYear, LocalDate jointime, String professional, Department department){
        return repository.insert(name, gender, birthYear, jointime, professional, department);
    }

    public DoctorService() {
    }

    //修改医生
    public Doctor updateDoctor(long id,String name, Gender gender, LocalDate birthYear, LocalDate jointime, String professional, Department department){
        Doctor doctor = new Doctor();
        doctor.setJoinDate(jointime);
        doctor.setBirthYear(birthYear);
        doctor.setName(name);
        doctor.setProfessional(professional);
        doctor.setGender(gender);
        doctor.setDepartment(department);
        return repository.update(id,doctor);
    }

    //删除医生
    public Doctor deleteDoctor(long id){
        return repository.delete(id);

    }
    //查询科室数量是否为空
    public boolean checkNumber(){
        return repository.check();
    }

    //通过id来查询医生信息
    public Doctor searchById(long id){
        return repository.searchById(id);
    }

    //通过医生名称查询医生信息
    public Doctor searchByDoctorName(String doctorName){
        List<Doctor> doctors = repository.searchByDoctorName(doctorName);
        return doctors.get(0);
    }
}
