package com.mohk.fleetmanagement.hr.repositories;

import com.mohk.fleetmanagement.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByUsername(String un);

    @Query(value = "select * from Employee e where e.firstname like %:keyword% or e.lastname like %:keyword%", nativeQuery = true)
    List<Employee> findByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT city, count(*) FROM Employee GROUP BY city",
            nativeQuery = true)
    List<Object> getCountByCountry();
}
