package com.example.attendance.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.attendance.dao.AttendanceDao;
import com.example.attendance.dao.StudentDao;
import com.example.attendance.model.StudentAttendance;
import com.example.attendance.model.Transaction;
import com.example.attendance.service.EmployeeService;
import com.example.attendance.utility.ApplicationConstants;
import com.example.attendance.utility.ExcelGenerator;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired(required = true)
	private EmployeeService employeeService;

	@Autowired(required = true)
	private ExcelGenerator excelGenerator;

	@Autowired(required = true)
	private StudentDao studentDao;

	@Autowired(required = true)
	private AttendanceDao attendanceDao;

	static List<Transaction> transactionList = new ArrayList<Transaction>();

	@GetMapping("/")
	public String registrationForm() {
		return "employee";
	}

	@GetMapping("/download-excel-sheet")
	public ResponseEntity<Resource> generateExcelSheet() throws IOException {
		employeeService.generateExcelSheet();
		String filename = "tutorials.xlsx";
		List<StudentAttendance> studentAttendanceList = studentDao.getAllStudents();
		ExcelGenerator excelGenerator = new ExcelGenerator(studentAttendanceList);
//		ExcelGenerator excelGenerator = new ExcelGenerator(new ArrayList<StudentAttendance>());
		excelGenerator.writeHeader(studentAttendanceList);
		excelGenerator.write();
		InputStreamResource file = new InputStreamResource(excelGenerator.load());
		System.err.println(excelGenerator.workbook);
		FileOutputStream fileOut = new FileOutputStream("/home/prasanna/Personal/sts-output/attendance.xlsx");
		excelGenerator.workbook.write(fileOut);
		return null;
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//	        .body(file);
	}
//	
//	@GetMapping("/download")
//	  public ResponseEntity<Resource> getFile() {
//	    String filename = "tutorials.xlsx";
//	    InputStreamResource file = new InputStreamResource(fileService.load());
//
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//	        .body(file);
//	  }

	@GetMapping("attendance-home-page")
	public String AttendanceHomePage(Model model) {
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		model.addAttribute("centerList", ApplicationConstants.CENTER_NAME);
		return "attendance-home-page";
	}

	@PostMapping("/mark-attendance")
	public String getAccountInfo(@RequestParam("subjectName") String subjectName, @RequestParam("date") String date,
			@RequestParam("centerName") String centerName, Model model) throws ParseException {
		System.err.println("subject = " + subjectName);
		System.err.println("date = " + date);
		System.err.println("centerName = " + centerName);
		ApplicationConstants.attendanceDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		List<StudentAttendance> StudentAttendanceList = studentDao.getAllStudentsForAttendance(subjectName,
				ApplicationConstants.attendanceDate, centerName);
		System.err.println(StudentAttendanceList);
		model.addAttribute("studentList", StudentAttendanceList);
		Map<String, StudentAttendance> studentMap = new LinkedHashMap<String, StudentAttendance>();
		int i = 0;
		for (StudentAttendance student : StudentAttendanceList) {
			studentMap.put(Integer.toString(i++), student);
		}
		System.err.println(studentMap);
		model.addAttribute("studentMap", studentMap);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("attendanceDate", date);
		model.addAttribute("centerName", centerName);
//		System.err.println(subjectDao.findAll());
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		return "mark-attendance";
	}
	
	@GetMapping("/search-absent-students")
	public String searchAbsentStudents(Model model) {
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		model.addAttribute("centerList", ApplicationConstants.CENTER_NAME);
		return "search-absent-students";
	}
	
	@PostMapping("/absent-student-list")
	public String absentStudentList(@RequestParam("subjectName") String subjectName, @RequestParam("date") String date,
			@RequestParam("centerName") String centerName, Model model) throws ParseException {
		System.err.println("subject = " + subjectName);
		System.err.println("date = " + date);
		System.err.println("centerName = " + centerName);
		ApplicationConstants.attendanceDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		List<StudentAttendance> StudentAttendanceList = studentDao.getAllStudentsForAttendance(subjectName,
				ApplicationConstants.attendanceDate, centerName);
		System.err.println(StudentAttendanceList);
		List<StudentAttendance> filteredstudentAttendanceList=null;
		if(StudentAttendanceList!=null)
		{
			filteredstudentAttendanceList=StudentAttendanceList.stream().filter(x->x.getAttendance().isStatus()==false).toList();
		}
		System.err.println(filteredstudentAttendanceList);
		model.addAttribute("studentList", filteredstudentAttendanceList);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("attendanceDate", date);
		model.addAttribute("centerName", centerName);
		return "absent-student-list";
	}

}
