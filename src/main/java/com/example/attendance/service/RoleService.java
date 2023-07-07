package com.example.attendance.service;

import com.example.attendance.model.EmployeeDto;
import com.example.attendance.model.Role;

public interface RoleService {

	void saveEmployee(EmployeeDto employeeDto);

	Role findByName(String name);

}
