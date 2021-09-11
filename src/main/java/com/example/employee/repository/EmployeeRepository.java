package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findEmployeesByName(String name);

    @Modifying
    @Query("UPDATE Employee e SET e.avatar = :avatar WHERE e.id = :id")
    void updateAvatarById(@Param("id") int id, @Param("avatar") String avatar);

}
