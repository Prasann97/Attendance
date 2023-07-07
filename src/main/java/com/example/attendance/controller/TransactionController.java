package com.example.attendance.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.attendance.dao.AttendanceDao;
import com.example.attendance.dao.StudentDao;
import com.example.attendance.model.Attendance;
import com.example.attendance.model.Student;
import com.example.attendance.model.StudentAttendance;
import com.example.attendance.model.Transaction;
import com.example.attendance.utility.ApplicationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TransactionController {

	static List<Transaction> transactionList =new ArrayList<Transaction>();
	
	@Autowired(required=true)
	private StudentDao studentDao;
	
	@Autowired(required=true)
	private AttendanceDao attendanceDao;
	
	@GetMapping("/upload-student-sheet")
	public String uploadStudentSheet(Model model)
	{
		model.addAttribute("monthList", ApplicationConstants.MONTH_LIST);
		return "upload-student-list";
	}
	
	@GetMapping("/")
	public String getHomePage(Model model)
	{
		return "home-page";
	}
	
//	@GetMapping("/login-page")
//	public String getLoginPage(Model model)
//	{
//		return "login";
//	}
	
	@PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String uploadMultipart(Model model,@RequestParam("file") MultipartFile file,@RequestParam("month") String month, @RequestParam("year") String year) throws IOException {
//        try {
//        	transactionList= CsvUtils.read(Transaction.class, file.getInputStream());
			System.err.println(file+" "+month+" "+year);
			List<Student> studentList = new ArrayList<Student>();
			    XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			    XSSFSheet worksheet = workbook.getSheetAt(0);
			    
			    for(int i=2;i<worksheet.getPhysicalNumberOfRows() ;i++) {
			    	Student student = new Student();
			            
			        XSSFRow row = worksheet.getRow(i);
//			        student.setPrn( Integer.toString(new Date().getYear()+1900).substring(2)+(Integer.toString(new Date().getMonth()).length()==1 ? "0"+Integer.toString(new Date().getMonth()) : Integer.toString(new Date().getMonth())) +Integer.toString((int) row.getCell(0).getNumericCellValue()));
					student.setPrn(year.substring(2) + "0" + (ApplicationConstants.MONTH_LIST.indexOf(month) + 1)+ Integer.toString((int) row.getCell(0).getNumericCellValue()));
			        student.setStudentName(row.getCell(1).getStringCellValue());
			        if(Integer.toString((int) row.getCell(0).getNumericCellValue())!=null && Integer.toString((int) row.getCell(0).getNumericCellValue()).startsWith("405"))
			        	student.setCenterName("Juhu");
			        else
			        	student.setCenterName("Kharghar");
			        studentList.add(student);
			    }
			    studentDao.saveAllStudnets(studentList);
			    System.err.println("studentList = "+studentList);
			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			    model.addAttribute("studentList", studentList);
        return "student-list";
    }
	
	@GetMapping("/get-account-information")
	public String getAccountInfo(Model model)
	{
		List<StudentAttendance> StudentAttendanceList=studentDao.getAllStudents();
		System.err.println(StudentAttendanceList);
		model.addAttribute("studentList", StudentAttendanceList);
		Map<String,StudentAttendance> studentMap=new LinkedHashMap<String,StudentAttendance>();
		int i=0;
		for (StudentAttendance student : StudentAttendanceList) {
			studentMap.put(Integer.toString(i++), student);
		}
		System.err.println(studentMap);
		model.addAttribute("studentMap", studentMap);
//		System.err.println(subjectDao.findAll());
		model.addAttribute("subjectList", ApplicationConstants.SUBJECT_NAME);
		return "get-account-information";
	}
	@PostMapping("save-attendance")
	public String saveAttendance(@RequestBody String studentListObject, Model model) throws JsonMappingException, JsonProcessingException, ParseException
	{
		System.err.println(studentListObject);
		ObjectMapper mapper = new ObjectMapper();
		List<StudentAttendance> studentAttendanceList = new ArrayList<>();
		studentAttendanceList = Arrays.asList(mapper.readValue(studentListObject, StudentAttendance[].class));
		
		System.err.println(studentAttendanceList);
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		for(StudentAttendance studentAttendance:studentAttendanceList)
		{
			studentAttendance.getAttendance().setAttendanceDate(ApplicationConstants.attendanceDate);
			attendanceList.add(studentAttendance.getAttendance());
		}
			
		attendanceDao.saveAll(attendanceList);
		return null;
	}

}
