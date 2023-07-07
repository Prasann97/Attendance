package com.example.attendance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	private String prn;
	private String StudentName;
	private String centerName;
//	private String subjectName;
//	@OneToOne
//	private Attendance attendance;
	
//	@OneToMany
//	private List<Attendance> dateWiseStudentAttendance;

	
	
	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	@Override
	public String toString() {
		return "Student [prn=" + prn + ", StudentName=" + StudentName + ", centerName=" + centerName + "]";
	}

	
}
