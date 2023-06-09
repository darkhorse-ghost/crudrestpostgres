package com.crud.rest.postgres.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.rest.postgres.exception.ProblemDetailException;
import com.crud.rest.postgres.model.Employee;
import com.crud.rest.postgres.repository.EmployeeRepository;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * get all employees
	 * 
	 * @return List<Employee>
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(employeeRepository.findAll());
	}

	/**
	 * create employee rest api
	 * 
	 * @param employee object
	 * @return Employee
	 */
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeRepository.save(employee));
	}

	/**
	 * get employee by id rest api
	 * 
	 * @param id
	 * @return Employee
	 */
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ProblemDetailException(HttpStatus.NOT_FOUND, "Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}

	/**
	 * update employee rest api
	 * 
	 * @param id
	 * @param employeeDetails
	 * @return Employee
	 */

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ProblemDetailException(HttpStatus.NOT_FOUND, "Employee not exist with id :" + id));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	/**
	 * partial update employee rest api
	 * 
	 * @param id
	 * @param employeeDetails
	 * @return Employee
	 */
	@PatchMapping("/employees/{id}")
	public ResponseEntity<Employee> partialUpdateEmployee(@PathVariable Long id,
			@RequestBody Employee employeeDetails) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ProblemDetailException(HttpStatus.NOT_FOUND, "Employee not exist with id :" + id));
		Optional.ofNullable(employeeDetails.getFirstName()).ifPresent(firstName -> employee.setFirstName(firstName));
		Optional.ofNullable(employeeDetails.getLastName()).ifPresent(lastName -> employee.setLastName(lastName));
		Optional.ofNullable(employeeDetails.getEmailId()).ifPresent(emailId -> employee.setEmailId(emailId));
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	/**
	 * delete employee rest api
	 * 
	 * @param id
	 * @return Map<String, Boolean>
	 */
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new ProblemDetailException(HttpStatus.NOT_FOUND, "Employee not exist with id :" + id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
