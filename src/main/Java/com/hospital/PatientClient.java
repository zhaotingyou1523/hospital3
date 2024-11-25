package main.Java.com.hospital;


import main.Java.com.hospital.domain.Gender;
import main.Java.com.hospital.domain.Patient;
import main.Java.com.hospital.message.MessageContext;
import main.Java.com.hospital.service.PatientService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PatientClient {
    Integer id = 1;
    Scanner sc = new Scanner(System.in);
    PatientService patientService = new PatientService();

    // 患者管理系统
    public void patientManager(){
        while (true) {
            System.out.println("=======환자 관리=======");
            System.out.println("1、환자 추가");
            System.out.println("2、환자 수정");
            System.out.println("3、환자 삭제");
            System.out.println("4、환자 조회");
            System.out.println("5、return");
            System.out.println("실행할 명령어를 입력하세요：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
            }catch (NumberFormatException e){
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요！");
                continue;
            }
            if (command < 1 || command > 5) {
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요！");
                continue;
            }
            switch (command) {
                case 1:
                    add();
                    break;
                case 2:
                    updateManager();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    searchPatientManager();
                    break;
                case 5:
                    System.out.println("return");
                    return;
            }
        }
    }

    // 修改患者数据
    public void updateManager() {
        if (!patientService.checkNumber()) {
            System.out.println("현재 시스템에 환자가 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요.");
            return;
        }
        Patient patient = searchPatientById("수정");

        while (true) {
            System.out.println("=======환자 과리=======");
            System.out.println("이름：" + patient.getName() + "\n성별：" + patient.getGender() + "\n생년월일：" + patient.getBirthYear() + "\n환자 정보：" + patient.getDescription() + "\n환자 전화번호：" + patient.getPhoneNumber());
            System.out.println("1、환자 이름 수정");
            System.out.println("2、환자 성별 수정 ");
            System.out.println("3、환자 생년월일 수정");
            System.out.println("4、환자 정보 수정");
            System.out.println("5、환자 전화번 수정");
            System.out.println("6、중지");
            System.out.println("실행할 명령어를 입력하세요：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
            }catch (NumberFormatException e){
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요");
                continue;
            }
            if (command < 1 || command > 6) {
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요");
                continue;
            }
            switch (command) {
                case 1:
                    patient.setName(setString("수정한 환자 이름을 입력하세요："));
                    break;
                case 2:
                    patient.setGender(getUserGenderChoice("수정한 환자 성별을 입력하세요：M，F"));
                    break;
                case 3:
                    patient.setBirthYear(setDate("수정한 환자 생년월일을 입력하세요(yyyy-MM-dd)："));
                    break;
                case 4:
                    patient.setDescription(setString("수정한 환자 정보을 입력하세요："));
                    break;
                case 5:
                    patient.setPhoneNumber(setString("수정한 환자 전화번호을 입력하세요："));
                    break;
                case 6:
                    System.out.println("return");
                    return;
            }
        }
    }

    // 患者查询系统
    public void searchPatientManager(){
        if (!patientService.checkNumber()) {
            System.out.println("현재 시스템에 과실가 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요");
            return;
        }
        while (true) {
            System.out.println("=======환자 관리=======");
            System.out.println("1、Id로 조희");
            System.out.println("2、이름로 조회");
            System.out.println("실행할 명령어를 입력하세요：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
            }catch (NumberFormatException e){
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요");
                continue;
            }
            if (command < 1 || command > 2) {
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요");
                continue;
            }
            switch (command) {
                case 1:
                    searchPatientById("조회");
                    return;
                case 2:
                    searchByPatientName("조회");
                    return;
            }
        }
    }

    // 添加患者
    public void add() {
        String patientName = setString("환자 이름을 입력하세요：");
        Gender gender = getUserGenderChoice("환자 성별을 입력하세요：M，F");
        LocalDate birthDate = setDate("환자 생년월일을 입력하세요(yyyy-MM-dd)：");
        String phoneNumber = setString("환자 전화ㅏ번호을 입력하세요：");
        String professional = setString("환자 정보을 입력하세요：");
        Patient patient = patientService.addPatient(patientName,gender,phoneNumber,professional,birthDate);
        System.out.println("您成功添加" + "\n患者名：" + patientName);
        id++;
    }

    // 删除医生
    public void delete(){
        if (!patientService.checkNumber()) {
            System.out.println("현재 시스템에 환자가 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요.");
            return;
        }
        while (true) {
            try {
                System.out.println("삭제할 의사ID을 입력해주세요：");
                int deid = sc.nextInt();
                patientService.deletePatient(id);
                System.out.println("삭제 성공" + "id:" + deid);
                return;
            }catch (InputMismatchException e){
                System.out.println("입력한 ID가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    // 设置医生名字
    public String setString(String message){
        System.out.println(message);
        return sc.next();
    }

    // 设置医生性别
    public Gender getUserGenderChoice(String message) {
        while (true) {
            try {
                System.out.print(message);
                int choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= Gender.values().length) {
                    return Gender.values()[choice - 1];
                } else {
                    System.out.println(MessageContext.genderError);
                }
            } catch (NumberFormatException e) {
                System.out.println(MessageContext.genderError);
            }
        }
    }

    // 将输入的日期转换为DateTime形式输出
    public LocalDate setDate(String message) {
        while (true) {
            System.out.println(message);
            String birthYear = sc.next();
            try {
                return LocalDate.parse(birthYear, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (InputMismatchException e) {
                System.out.println("입력하신 시간 형식이 잘못되었습니다. 다시 입력해 주세요.");
            }
        }
    }

    // 设置id搜索医生
    public Patient searchPatientById(String type){
        while (true) {
            try {
                System.out.println("조회할"+ type +"환자 id：");
                Integer id = sc.nextInt();
                Patient patient = patientService.searchById(id);
                System.out.println("성공");
                System.out.println("id：" + patient.getId() + "\n" + "환자명：" + patient.getName()+ "\n성별：" + patient.getGender() + "\n생년월일：" + patient.getBirthYear() + "\n환자 정보：" + patient.getDescription() +  "\n환자 전화번호：" + patient.getPhoneNumber());
                return patient;
            }catch (InputMismatchException e){
                System.out.println("입력하신 id가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    // 通过医生名字搜索医生
    public Patient searchByPatientName(String type){
        while (true) {
            try {
                System.out.println("조회할" + type + "의사 이름：");
                String patientName = sc.next();
                Patient patient = patientService.searchByDoctorName(patientName);
                System.out.println("성공");
                System.out.println("id：" + patient.getId() + "\n" + "환자명：" + patient.getName() + "\n성별：" + patient.getGender() + "\n생년월일：" + patient.getBirthYear() + "\n환자 정보：" + patient.getDescription() + "\n환자 전화번호：" + patient.getPhoneNumber());
                return patient;
            }catch (InputMismatchException e){
                System.out.println("입력하신 의사이름가 잘못되었습니다. 다시 입력해 주세요");
            }

        }
    }
}
