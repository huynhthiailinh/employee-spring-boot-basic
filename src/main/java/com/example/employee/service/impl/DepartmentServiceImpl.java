package com.example.employee.service.impl;

import com.example.employee.model.Department;
import com.example.employee.repository.DepartmentRepository;
import com.example.employee.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }
}
