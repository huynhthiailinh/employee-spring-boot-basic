package com.example.employee.controller;

import com.example.employee.handleJsonFilter.EmployeeFilter;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeFilter employeeFilter;

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity addEployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeFilter.getEmployee(employeeService.addEmployee(employee)), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity getEmployeeById(@PathVariable int id) {
        return new ResponseEntity<>(employeeFilter.getEmployee(employeeService.getEmployeeById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getEmployees() {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployees()), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity("Deleted!", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeFilter.getEmployee(employeeService.updateEmployee(employee)), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity getEmployeesByName(@RequestParam String name) {
        return new ResponseEntity<>(employeeFilter.getEmployees(employeeService.getEmployeesByName(name)), HttpStatus.OK);
    }
}
