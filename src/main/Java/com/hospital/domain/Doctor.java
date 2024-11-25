package main.Java.com.hospital.domain;

import java.time.LocalDate;

public class Doctor {
    //医生
    private long id; // 번호
    private String name; // 이름
    private Department department; // 과실
    private Gender gender; // 성별
    private LocalDate birthYear; // 생년월일
    private String Professional; // 전문
    private LocalDate joinDate; //가입시간

    @Override
    public String toString() {
        return "Doctor{" +
                 name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(LocalDate birthYear) {
        this.birthYear = birthYear;
    }

    public String getProfessional() {
        return Professional;
    }

    public void setProfessional(String professional) {
        Professional = professional;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
