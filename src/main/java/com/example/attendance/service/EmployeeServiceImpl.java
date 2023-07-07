package com.example.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.attendance.dao.EmployeeDao;
import com.example.attendance.dao.RoleDao;
import com.example.attendance.model.Employee;
import com.example.attendance.model.EmployeeDto;
import com.example.attendance.model.Role;
import com.example.attendance.utility.ApplicationConstants;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private RoleDao roleDao;
	
//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(EmployeeDto employeeDto) {
		 Role role = roleDao.findByName(ApplicationConstants.USER);

	        if (role == null)
	            role = roleDao.save(new Role(ApplicationConstants.USER));

//	        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmail(), passwordEncoder.encode(employeeDto.getPassword()),
//	                Arrays.asList(role));
//	        employeeDao.save(employee);
		
	}

	@Override
	public Employee findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateExcelSheet() {

		
	}

}
