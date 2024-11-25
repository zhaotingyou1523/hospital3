package main.Java.com.hospital.service;
import main.Java.com.hospital.domain.Gender;
import main.Java.com.hospital.domain.Patient;
import main.Java.com.hospital.repository.PatientRepository;
import java.time.LocalDate;
import java.util.List;

public class PatientService {
    private PatientRepository repository = new PatientRepository();
    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public PatientService() {
    }

    // 添加患者
    public Patient addPatient(String name, Gender gender, String phoneNumber, String description, LocalDate birthYear){
        return repository.insert(name, gender, phoneNumber, description, birthYear);
    }

    // 修改患者
    public Patient updatePatient(Integer id,String name, Gender gender, String phoneNumber, String description, LocalDate birthYear){
        Patient patient = new Patient();
        patient.setPhoneNumber(phoneNumber);
        patient.setGender(gender);
        patient.setBirthYear(birthYear);
        patient.setDescription(description);
        patient.setName(name);
        return repository.update(id,patient);
    }

    //删除医生
    public Patient deletePatient(Integer id){
        return repository.delete(id);

    }

    //查询患者数量是否为空
    public boolean checkNumber(){
        return repository.check();
    }

    //通过id来查询医生信息
    public Patient searchById(Integer id){
        return repository.searchById(id);
    }

    //通过医生名称查询医生信息
    public Patient searchByDoctorName(String patientName){
        List<Patient> patient = repository.searchByPatientName(patientName);
        return patient.get(0);
    }
}
