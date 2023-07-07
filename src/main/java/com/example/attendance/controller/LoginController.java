package com.example.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.attendance.model.Employee;
import com.example.attendance.model.EmployeeDto;
import com.example.attendance.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/login")
    public String loginForm(Model model) {
    	model.addAttribute("employee", new EmployeeDto());
        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee", employee);
        return "registration";
    }
    
    @PostMapping("/login")
    public String loginForm1(@Valid @ModelAttribute("employee") EmployeeDto employeeDto,
            BindingResult result,
            Model model) {
    	System.err.println(employeeDto);
        return "login";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("employee") EmployeeDto employeeDto,
            BindingResult result,
            Model model) {
        Employee existingUser = employeeService.findUserByEmail(employeeDto.getEmail());

        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        if (result.hasErrors()) {
            model.addAttribute("user", employeeDto);
            return "/registration";
        }

        employeeService.saveUser(employeeDto);
        return "redirect:/registration?success";
    }
}