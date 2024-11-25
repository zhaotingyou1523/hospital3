package main.Java.com.hospital.repository;

import main.Java.com.hospital.domain.Appointment;
import main.Java.com.hospital.domain.Department;
import main.Java.com.hospital.domain.Doctor;
import main.Java.com.hospital.domain.Patient;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// 예약 데이터베이스
public class AppointmentRepository {
    private long id = 0L;
    private static Map<Long, Appointment> appointmentMap = new HashMap<>();

    // 예약추가
    public synchronized Appointment insert(Doctor doctor, Patient patient, LocalDateTime appointTime, Department department){
        long appointId = ++id;
        Appointment appointment = new Appointment();
        appointment.setAppointTime(appointTime);
        appointment.setDepartment(department);
        appointment.setDoctor(doctor);
        appointment.setId(appointId);
        appointment.setPatient(patient);
        return appointmentMap.put(appointment.getId(),appointment);
    }

    // 환자 데이터 수정
    public synchronized Appointment update(long id,Appointment appointment){
        Appointment apt = appointmentMap.get(id);
        apt.setAppointTime(appointment.getAppointTime());
        apt.setDepartment(appointment.getDepartment());
        apt.setDoctor(appointment.getDoctor());
        apt.setPatient(appointment.getPatient());
        return apt;
    }

    // 환자 삭제
    public synchronized Appointment delete(long id){
        return appointmentMap.remove(id);
    }

}
