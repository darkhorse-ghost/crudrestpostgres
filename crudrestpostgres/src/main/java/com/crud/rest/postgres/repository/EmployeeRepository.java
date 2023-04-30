package com.crud.rest.postgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.rest.postgres.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
