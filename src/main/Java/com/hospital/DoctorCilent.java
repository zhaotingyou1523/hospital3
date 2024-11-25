package main.Java.com.hospital;
/**
 *
 */

import main.Java.com.hospital.domain.Department;
import main.Java.com.hospital.domain.Doctor;
import main.Java.com.hospital.domain.Gender;
import main.Java.com.hospital.message.MessageContext;
import main.Java.com.hospital.service.DepartmentService;
import main.Java.com.hospital.service.DoctorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DoctorCilent extends DepartmentCilent{
    long id = 1L;
    Scanner sc = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentService();
    DoctorService doctorService = new DoctorService();

    // 医生管理系统
    public void doctorManager(){
        while (true) {
            System.out.println("======의사 관리=======");
            System.out.println("1、의사 추가");
            System.out.println("2、의사 정보 수정");
            System.out.println("3、의사 삭제");
            System.out.println("4、의사 정보 조회.");
            System.out.println("5、return");
            System.out.println("실행할 명령어를 입력하세요.：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
            }catch (NumberFormatException e){
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
                continue;
            }
            if (command < 1 || command > 5) {
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
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
                    searchDoctorManager();
                    break;
                case 5:
                    System.out.println("return");
                    return;
            }
        }
    }


    // 修改医生数据
    public void updateManager() {
        if (!doctorService.checkNumber()) {
            System.out.println("현재 시스템에 의사가 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요.");
            return;
        }
        Doctor doctor = searchDoctorById("修改");

        while (true) {
            System.out.println("=======의사 관리=======");
            System.out.println("성명：" + doctor.getName() + "\n성별：" + doctor.getGender() + "\n생년월일：" + doctor.getBirthYear() + "\n가입시간：" + doctor.getJoinDate() + "\n과실：" + doctor.getDepartment());
            System.out.println("1、의사이름 수정");
            System.out.println("2、의사성별 수정");
            System.out.println("3、의사생년월일 수정");
            System.out.println("4、의사가입시간 수정");
            System.out.println("5、의사과실 수정");
            System.out.println("6、의사전문 수정");
            System.out.println("7、중지");
            System.out.println("실행할 명령어를 입력하세요.：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
            }catch (NumberFormatException e){
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
                continue;
            }
            if (command < 1 || command > 6) {
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
                continue;
            }
            switch (command) {
                case 1:
                    doctor.setName(setDoctorName("수정한 이름을 입력해 주세요.："));
                    break;
                case 2:
                    doctor.setGender(getUserGenderChoice("수정한 성별을 입력해 주세요.：M，F"));
                    break;
                case 3:
                    doctor.setBirthYear(setDate("수정한 의사 새년월일을 입력해 주세요.(yyyy-MM-dd)："));
                    break;
                case 4:
                    doctor.setJoinDate(setDate("수정한 가입시간을 입력해 주세요.(yyyy-MM-dd)："));
                    break;
                case 5:
                    doctor.setDepartment(setDepartment("과실 ID을 입력해 주세요："));
                    break;
                case 6:
                    System.out.println("의사 ID을 입력해 주세요：");
                    doctor.setProfessional(sc.next());
                    break;
                case 7:
                    System.out.println("return");
                    return;
            }
        }
    }

    // 医生查询系统
    public void searchDoctorManager(){
        if (!doctorService.checkNumber()) {
            System.out.println("현재 시스템에 과실이 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요");
            return;
        }
        while (true) {
            System.out.println("=======의사 관리=======");
            System.out.println("1、ID로 조회");
            System.out.println("2、이름로 조회");
            System.out.println("실행할 명령어를 입력하세요.：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
            }catch (NumberFormatException e){
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
                continue;
            }
            if (command < 1 || command > 2) {
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
                continue;
            }
            switch (command) {
                case 1:
                    searchDoctorById("조회");
                    return;
                case 2:
                    searchByDoctortName("조회");
                    return;
            }
        }
    }

    // 添加医生
    public void add() {
        if (!departmentService.checkNumber()) {
            System.out.println("현재 시스템에 아직 과실를 추가하지 않았습니다. 추가 후 다시 시도해 주세요.");
            return;
        }
        String doctorName = setDoctorName("의사 이름을 입력하세요：");
        Gender gender = getUserGenderChoice("의사 성별을 입력하세요：M，F");
        LocalDate birthDate = setDate("의사 생년월일을 입력하세요(yyyy-MM-dd)：");
        LocalDate joinDate = setDate1("의사 가입시간을 입력하세요(yyyy-MM-dd)：",birthDate);
        System.out.println("의사 전문을 입력하세요：");
        String professional = sc.next();
        Department department = setDepartment("과실 ID을 입력하세요：");
        Doctor docotor = doctorService.addDoctor(doctorName,gender,birthDate,joinDate,professional,department);
        System.out.println( "의사ID" + docotor.getId() + "\n의사명：" + doctorName + "\n과실명："+ docotor.getDepartment().getName());
        id++;
    }

    // 删除医生
    public void delete(){
        if (!doctorService.checkNumber()) {
            System.out.println("현재 시스템에 의사가 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요\"");
            return;
        }
        while (true) {
            try {
                System.out.println("삭제할 의사ID을 입력해주세요：");
                long deid = sc.nextInt();
                doctorService.deleteDoctor(id);
                System.out.println("삭제 성공:" + "id:" + deid);
                return;
            }catch (InputMismatchException e){
                System.out.println("입력한 ID가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    // 设置医生名字
    public String setDoctorName(String message){
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
                    LocalDate date = LocalDate.parse(birthYear, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return date;
            } catch (InputMismatchException e) {
                System.out.println("입력하신 시간 형식이 잘못되었습니다. 다시 입력해 주세요.");
            }
        }
    }
    public LocalDate setDate1(String message,LocalDate birthday) {
        while (true) {
            System.out.println(message);
            String birthYear = sc.next();
            try {
                LocalDate date = LocalDate.parse(birthYear, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (!date.isBefore(birthday)) {
                    return date;
                }else {
                    System.out.println("다시 입력해 주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("입력하신 시간 형식이 잘못되었습니다. 다시 입력해 주세요.");
            }
        }
    }

    // 设置医生科室名
    public Department setDepartment(String message){
            System.out.println(message);
            int idd = sc.nextInt();
        return departmentService.searchById(idd);
        }

    // 设置id搜索医生
    public Doctor searchDoctorById(String type){
        while (true) {
            try {
                System.out.println("조회할"+ type +" 의사 id：");
                long id = sc.nextInt();
                Doctor doctor = doctorService.searchById(id);
                System.out.println("성공");
                System.out.println( "의사 이름：" + doctor.getName()+ "\n성별：" + doctor.getGender() + "\n생년윌일：" + doctor.getBirthYear() + "\n가입시간：" + doctor.getJoinDate() + "\n전문：" + doctor.getProfessional() + "\n과실：" + doctor.getDepartment().getName());
                return doctor;
            }catch (InputMismatchException e){
                System.out.println("입력하신 의사 ID가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    // 通过医生名字搜索医生
    public Doctor searchByDoctortName(String type){
        while (true) {
            try {
                System.out.println("조회할" + type + "의사 이름：");
                String doctorName = sc.next();
                Doctor doctor = doctorService.searchByDoctorName(doctorName);
                System.out.println("성공");
                System.out.println("id：" + doctor.getId() + "\n" + "의사 이름：" + doctor.getName() + "\n성별：" + doctor.getGender() + "\n생년윌일：" + doctor.getBirthYear() + "\n가입시간：" + doctor.getJoinDate() + "\n전문：" + doctor.getProfessional() + "\n과실：" + doctor.getDepartment());
                return doctor;
            }catch (InputMismatchException e){
                System.out.println("입력하신 의사 이름가 잘못되었습니다. 다시 입력해 주세요");
            }

        }
    }
}
