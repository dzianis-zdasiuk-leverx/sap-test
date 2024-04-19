package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping("/employee/count")
	public long count() {
		log.info("Search total number of employees");
		return employeeService.getCount();
	}
	
	@RequestMapping("/employee/all")
	public List<Employee> getAllEmployees(){
		log.info("Searching all employees");
		return employeeService.findAllEmployee();
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/employee/add")
	public boolean addEmployee(@RequestBody Employee employee) {
		
		log.info("Creation/Updating Employee - "+employee.toString());
		return employeeService.insertEmployee(employee);
	}
	
	@RequestMapping("/employee/id/{id}" )
	public Employee findById(@PathVariable long id) {
		log.info("Searching employee with ID - "+ id);
		return employeeService.findEmployeeById(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employee/delete/{id}")
	public boolean deleteEmployee(@PathVariable long id) {
		return employeeService.deleteEmployee(id);
	}
	
}