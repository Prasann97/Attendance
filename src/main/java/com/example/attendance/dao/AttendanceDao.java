package com.example.attendance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.attendance.model.Attendance;

public interface AttendanceDao extends JpaRepository<Attendance, Long>{

}
