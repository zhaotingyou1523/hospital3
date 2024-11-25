package main.Java.com.hospital.service;


import main.Java.com.hospital.domain.Appointment;
import main.Java.com.hospital.domain.Department;
import main.Java.com.hospital.domain.Doctor;
import main.Java.com.hospital.domain.Patient;
import main.Java.com.hospital.repository.AppointmentRepository;

import java.time.LocalDateTime;

public class AppointmentService {
    private DoctorService doctorService;
    private PatientService patientService;
    private DepartmentService departmentService;
    private AppointmentRepository repository = new AppointmentRepository();

    public AppointmentService() {
    }

    public AppointmentService(DoctorService doctorService, PatientService patientService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.departmentService = departmentService;
    }
    public Appointment addAppointment(Doctor doctor, Patient patient, LocalDateTime appointTime, Department department){
        return repository.insert(doctor,patient,appointTime,department);
    }
    public Appointment updateAppointment(long id,Patient patient,Doctor doctor,Department department,LocalDateTime appointTime){
        Appointment appointment = new Appointment();
        appointment.setAppointTime(appointTime);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDepartment(department);
        return repository.update(id,appointment);
    }
    public Appointment deleteAppointment(long id){
        return repository.delete(id);
    }

}
