package main.Java.com.hospital;


import main.Java.com.hospital.domain.Department;
import main.Java.com.hospital.repository.DepartmentRepository;
import main.Java.com.hospital.service.DepartmentService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DepartmentCilent {
    long id = 1;
    Scanner sc = new Scanner(System.in);
    DepartmentService departmentService = new DepartmentService();
    DepartmentRepository departmentRepository = new DepartmentRepository();

    public void departmentManager() {
        while (true) {
            System.out.println("=======과실 관리=======");
            System.out.println("1、과실 추가");
            System.out.println("2、과실 수정");
            System.out.println("3、과실 삭제");
            System.out.println("4、과실 조회");
            System.out.println("실행할 명령어를 입력하세요.：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
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
            System.out.println("현재 시스템에 과실이 추가되어 있지 않습니다. 추가한 후에 다시 시도해 주세요.");
            return;
        }
        while (true) {
            System.out.println("=======과실 관리=======");
            System.out.println("1、ID 조회");
            System.out.println("2、이름 조회");
            System.out.println("실행할 명령어를 입력하세요.：");
            int command = 0;
            try {
                command = Integer.parseInt(sc.next());
            }catch (NumberFormatException e){
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요.");
                continue;
            }
            if (command < 1 || command > 2) {
                System.out.println("입력하신 명령이 잘못되었습니다. 다시 입력해주세요.");
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
                System.out.println("：");
                String departmentname = sc.next();
                Department department = departmentService.addDepartment(departmentname);
                System.out.println("성공적으로 추가되었습니다:" + "id:" + department.getId() +  "\n과실：" + departmentname);
                id++;
        }

    public void update() {
            if (!departmentService.checkNumber()) {
                System.out.println("현재 시스템에 아직 과실를 추가하지 않았습니다. 추가 후 다시 시도해 주세요.");
                return;
            }
        while (true) {
                try {
                    System.out.println("수정할 과실 ID를 입력해 주세요：");
                    long id = sc.nextInt();
                    System.out.println("수정할 과실 이름를 입력해 주세요.：");
                    String departmentnewname = sc.next();
                    departmentService.updateDepartment(id, departmentnewname);

                System.out.println("수정이 완료되었습니다! 부서 이름이 다음으로 변경되었습니다.：" + departmentnewname);
                return;
                }catch (InputMismatchException e){
                    System.out.println("입력하신 ID가 잘못되었습니다. 다시 입력해 주세요.");
                }

        }
    }

    public void delete(){
            if (!departmentService.checkNumber()) {
                System.out.println("현재 시스템에 아직 과실를 추가하지 않았습니다. 추가 후 다시 시도해 주세요.");
                return;
            }
        while (true) {
            try {
                System.out.println("삭제할 부서 ID를 입력해 주세요.：");
                long deid = sc.nextInt();
                departmentService.deleteDepartment(id);
                System.out.println("恭喜您成功删除" + "id:" + deid);
                return;
            }catch (InputMismatchException e){
                System.out.println("您输入的id有误，请重新输入");
            }

        }
    }

    public Department searchById(){
        while (true) {
            try {
                System.out.println("조회할 부서 ID를 입력해 주세요.：");
                long id = sc.nextInt();
                Department department = departmentService.searchById(id);
                System.out.println("성공");
                System.out.println("id：" + department.getId() + "\n" + "과실명：" + department.getName());
                return department;
            }catch (InputMismatchException e){
                System.out.println("입력하신 ID가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }

    public void searchBydepartmentName(){
        while (true) {
            try {
                System.out.println("조회할 부서 ID를 입력해 주세요.：");
                String departmentName = sc.next();
                Department department = departmentService.searchByDepartmentName(departmentName);
                System.out.println("성공");
                System.out.println("id：" + department.getId() + "\n" + "科室名：" + department.getName());
                return;
            }catch (InputMismatchException e){
                System.out.println("입력하신 이름가 잘못되었습니다. 다시 입력해 주세요.");
            }

        }
    }
}
