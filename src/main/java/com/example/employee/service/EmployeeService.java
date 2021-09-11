package com.example.employee.service;

import com.example.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getEmployees();

    void deleteEmployee(int id);

    Employee updateEmployee(Employee employee);

    List<Employee> getEmployeesByName(String name);

    void updateAvatarById(int id, String avatar);

}
