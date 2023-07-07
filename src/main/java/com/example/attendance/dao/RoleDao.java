package com.example.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance.model.Role;

public interface RoleDao extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
