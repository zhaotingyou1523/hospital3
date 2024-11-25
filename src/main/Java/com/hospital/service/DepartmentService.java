package main.Java.com.hospital.service;
import main.Java.com.hospital.domain.Department;
import main.Java.com.hospital.repository.DepartmentRepository;

import java.util.List;

public class DepartmentService {
    private DepartmentRepository repository = new DepartmentRepository();

    public DepartmentService() {
    }

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    //과실 추가
    public Department addDepartment(String name){
        return repository.insert(name);
    }

    //과실 수정
    public void updateDepartment(long id, String name){
        Department department = new Department();
        department.setName(name);
        repository.update(id, department);
    }

    //과실 삭제
    public void deleteDepartment(long id){
        repository.delete(id);
    }

    //查询科室数量是否为空
    public boolean checkNumber(){
        return repository.check();
    }

    //通过id来查询科室信息
    public Department searchById(long id){
        return repository.searchById(id);
    }

    //通过科室名称查询科室信息
    public Department searchByDepartmentName(String departmentName){
        List<Department> departments = repository.searchByDepartmentName(departmentName);
        return departments.get(0);
    }

}
