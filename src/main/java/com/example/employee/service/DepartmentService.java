package com.example.employee.service;

import com.example.employee.model.Department;

import java.util.List;

public interface DepartmentService {

    Department addDepartment(Department department);

    List<Department> getDepartments();

}
