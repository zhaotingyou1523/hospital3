package main.Java.com.hospital;


import main.Java.com.hospital.domain.Department;
import main.Java.com.hospital.service.AppointmentService;
import main.Java.com.hospital.service.DepartmentService;
import main.Java.com.hospital.service.DoctorService;
import main.Java.com.hospital.service.PatientService;

import java.util.InputMismatchException;
import java.util.Scanner;


public class AppointmentClient {
    long id = 1;
    Scanner sc = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentService();
    AppointmentService appointmentService = new AppointmentService();
    DoctorService doctorService = new DoctorService();
    PatientService patientService = new PatientService();
    public void appointmentManager() {
        while (true) {
            System.out.println("=======예약 관리=======");
            System.out.println("1、예약 추가");
            System.out.println("2、예약 수정");
            System.out.println("3、예약 삭제");
            System.out.println("4、예약 조회");
            System.out.println("실행할 명령어를 입력하세요.：");
            int command = 0;
            try {
                command = sc.nextInt();
            }catch (NumberFormatException e){
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
                continue;
            }
            if (command < 1 || command > 4) {
                System.out.println("입력한 명령어가 잘못되었습니다. 다시 입력해 주세요.");
                continue;
            }
            switch (command) {
                case 1:
                    add();
                    return;
                case 2:
                    update();
                    return;
                case 3:
                    delete();
                    return;
                case 4:
                    searchDepartmentManager();
                    return;

            }
        }
    }
    public void searchDepartmentManager(){
        if (!departmentService.checkNumber()) {
            System.out.println("현재 시스템에 예약이 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요.");
            return;
        }
        while (true) {
            System.out.println("=======예약 관리=======");
            System.out.println("1、ID 조회");
            System.out.println("2、이름 조회");
            System.out.println("실행할 명령어를 입력하세요.：");
            int command = 0;
            try {
                command = sc.nextInt();
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
                    searchById();
                    return;
                case 2:
                    searchBydepartmentName();
                    return;
            }
        }
    }

    public void add() {
        System.out.println("과실명을 입력해 주세요.：");
        String departmentname = sc.next();
        Department department = departmentService.addDepartment(departmentname);
        System.out.println("\n과실명：" + departmentname);
        id++;
    }

    public void update() {
        if (!departmentService.checkNumber()) {
            System.out.println("현재 시스템에 과실이 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요.");
            return;
        }
        while (true) {
            try {
                System.out.println("수정할 과실 ID를 입력해 주세요.：");
                long id = sc.nextInt();
                System.out.println("수정할 과실명을 입력해 주세요.：");
                String departmentnewname = sc.next();
                departmentService.updateDepartment(id, departmentnewname);

                System.out.println("축하합니다! 과실명이 성공적으로 변경되었습니다:：" + departmentnewname);
                return;
            }catch (InputMismatchException e){
                System.out.println("입력한 ID가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    public void delete(){
        if (!departmentService.checkNumber()) {
            System.out.println("현재 시스템에 과실이 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요.");
            return;
        }
        while (true) {
            try {
                System.out.println("삭제할 과실 ID를 입력해 주세요.：");
                long deid = sc.nextInt();
                departmentService.deleteDepartment(id);
                System.out.println("축하합니다! 성공적으로 삭제되었습니다:" + "id:" + deid);
                return;
            }catch (InputMismatchException e){
                System.out.println("입력한 ID가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    public void searchById(){
        while (true) {
            try {
                System.out.println("조회할 과실 ID를 입력해 주세요.：");
                long id = sc.nextInt();
                Department department = departmentService.searchById(id);
                System.out.println("검색 성공");
                System.out.println("id：" + department.getId() + "\n" + "과실명：" + department.getName());
                return;
            }catch (InputMismatchException e){
                System.out.println("입력한 ID가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    public void searchBydepartmentName(){
        while (true) {
            try {
                System.out.println("조회할 과실명을 입력해 주세요.：");
                String departmentName = sc.next();
                Department department = departmentService.searchByDepartmentName(departmentName);
                System.out.println("검색 성공");
                System.out.println("id：" + department.getName() + "\n" + "과실명：" + department.getName());
                return;
            }catch (InputMismatchException e){
                System.out.println("입력한 과실명이 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

}
