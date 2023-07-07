package com.example.attendance.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.attendance.dao.EmployeeDao;
import com.example.attendance.model.Employee;

public class CustomEmployeeDetails implements UserDetailsService{

	@Autowired
    private EmployeeDao employeeDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeDao.findEmployeeByEmail(username);
        if (employee != null) {
            return new org.springframework.security.core.userdetails.User(employee.getEmail()
                    , employee.getPassword(),
                    employee.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
	}

}
