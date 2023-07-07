package com.example.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.attendance.dao.EmployeeDao;
import com.example.attendance.dao.RoleDao;
import com.example.attendance.model.EmployeeDto;
import com.example.attendance.model.Role;
import com.example.attendance.utility.ApplicationConstants;

public class RoleServiceImpl implements RoleService{

	@Autowired
    private RoleDao roleDao;
	
	@Autowired
	private EmployeeDao	employeeDao;
	
//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveEmployee(EmployeeDto employeeDto) {
		Role role = roleDao.findByName(ApplicationConstants.USER);

        if (role == null)
            role = roleDao.save(new Role(ApplicationConstants.USER));

//        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmail(), passwordEncoder.encode(employeeDto.getPassword()),
//                Arrays.asList(role));
//        employeeDao.save(employee);
	}

	@Override
	public Role findByName(String name) {
		return roleDao.findByName(name);
	}

}
