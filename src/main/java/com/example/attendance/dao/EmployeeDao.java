package com.example.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

	Employee findEmployeeByEmail(String email);
}
