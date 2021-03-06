package com.example.employee.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonFilter("filter.Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    private String avatar;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
