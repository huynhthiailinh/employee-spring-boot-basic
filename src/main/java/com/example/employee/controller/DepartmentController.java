package com.example.employee.controller;

import com.example.employee.handleJsonFilter.DepartmentFilter;
import com.example.employee.model.Department;
import com.example.employee.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentFilter departmentFilter;

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentFilter.getDepartment(departmentService.addDepartment(department)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity<>(departmentFilter.getDepartments(departmentService.getDepartments()), HttpStatus.OK);
    }

}
