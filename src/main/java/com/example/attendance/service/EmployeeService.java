package com.example.attendance.service;

import com.example.attendance.model.Employee;
import com.example.attendance.model.EmployeeDto;

public interface EmployeeService {

	void saveUser(EmployeeDto userDto);

    Employee findUserByEmail(String email);
    
    void generateExcelSheet();

}
