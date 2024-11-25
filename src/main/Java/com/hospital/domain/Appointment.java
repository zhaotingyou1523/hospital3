package main.Java.com.hospital.domain;

import java.time.LocalDateTime;

public class Appointment {
    //예약
    private long id; // 번호
    private Patient patient; // 환자
    private Doctor doctor; // 의사
    private Department department ; // 과실
    private LocalDateTime appointTime; // 예약시간

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(LocalDateTime appointTime) {
        this.appointTime = appointTime;
    }
}
