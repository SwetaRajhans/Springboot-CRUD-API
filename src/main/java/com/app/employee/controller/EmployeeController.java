package com.app.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.employee.model.Employee;
import com.app.employee.repository.EmployeeRepository;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/getAllEmployee")
	public ResponseEntity<?> getAllEmployee() {
		List<Employee> employeelist=(List<Employee>) employeeRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(employeelist);
		}
	@GetMapping("/getEmployeeById/{employeeId}")
	public Optional<Employee> getEmployeeById(@PathVariable(value = "employeeId") Integer employeeId) {
		return employeeRepository.findById(employeeId);
	}
	@PostMapping("/addemployee")
	public ResponseEntity<?> addEmployee (@RequestBody Employee employee){
		Employee employeelist=employeeRepository.save(employee);
				return ResponseEntity.status(HttpStatus.OK).body(employeelist);
    }
	@PutMapping("/updateemployee/{employeeId}")
	public ResponseEntity<?> updateEmployee(@PathVariable(value = "employeeId") int employeeId, @RequestBody Employee employee)
	{
		Optional<Employee> employeelist=employeeRepository.findById(employeeId);
	    Employee employeeresult=employeelist.get();
		employeeresult.setEmployeeName(employee.getEmployeeName().toString());
		Employee employeeResponse=employeeRepository.save(employeeresult);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	@DeleteMapping("/deleteemployee/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "employeeId") int employeeId){
		employeeRepository.deleteById(employeeId);
		return ResponseEntity.status(HttpStatus.OK).body("deleted :"+employeeId);
	}
	
	
	
}
