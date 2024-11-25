package main.Java.com.hospital.repository;

import main.Java.com.hospital.domain.Department;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

// 과실 데이터베이스
public class DepartmentRepository {
    private long id = 0L;
    private static Map<Long, Department> departmentMap = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    // 과실 추가
    public synchronized Department insert(String name){
        long departmentId = ++id;
        Department department = new Department();
        department.setId(departmentId);
        department.setName(name);
        departmentMap.put(department.getId(),department);
        return department;

    }

    // 과실 수정.
    public synchronized Department update(long id,Department department){
        Department dep = departmentMap.get(id);
        dep.setName(department.getName());
        return dep;
    }

    // 과실 삭제
    public synchronized Department delete(long id){
        return departmentMap.remove(id);
    }

    //과실 수가 비어 있는지 확인합니다.
    public boolean check(){
        return !departmentMap.isEmpty();
    }

    //ID로 과실 정보를 조회
    public Department searchById(long departmentId){
        return departmentMap.values().stream().filter(department -> department.getId() == departmentId).findFirst().orElse(null);
    }

    //과실명으로 과실 정보 조회
    public List<Department> searchByDepartmentName(String name) {
        return departmentMap.values().stream().filter(department -> name.equals(department.getName())).collect(Collectors.toList());
    }

    public Department showAndSelectDepartment() {
        for (int i = 0; i < departmentMap.size(); i++) {
            System.out.println(departmentMap.get(i).getId() + "." + departmentMap.get(i).getName());
        }
        System.out.println("请输入您要选择的科室id：");
        long departementId = sc.nextInt();
        Department department = departmentMap.get(departementId);
        return department;
    }
    }



