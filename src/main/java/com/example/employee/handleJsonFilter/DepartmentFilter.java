package com.example.employee.handleJsonFilter;

import com.example.employee.model.Department;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class DepartmentFilter {

    public MappingJacksonValue getDepartment(Department department) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(department);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Department", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAllExcept("department"));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    public MappingJacksonValue getDepartments(List<Department> departmentList) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(departmentList);
        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("filter.Department", SimpleBeanPropertyFilter.serializeAll())
                .addFilter("filter.Employee", SimpleBeanPropertyFilter.serializeAllExcept("department"));
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

}
