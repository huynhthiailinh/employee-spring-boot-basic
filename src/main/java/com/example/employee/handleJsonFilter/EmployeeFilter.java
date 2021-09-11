package com.example.employee.handleJsonFilter;

import com.example.employee.model.Employee;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class EmployeeFilter {

    public MappingJacksonValue getEmployee(Employee employee) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employee);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.serializeAllExcept("employeeList"));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    public MappingJacksonValue getEmployees(List<Employee> employeeList) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(employeeList);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Department", SimpleBeanPropertyFilter.serializeAllExcept("employeeList"));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

}
