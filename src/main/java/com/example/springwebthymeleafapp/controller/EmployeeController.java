package com.example.springwebthymeleafapp.controller;

import com.example.springwebthymeleafapp.entity.Employee;
import com.example.springwebthymeleafapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("/map")
public class EmployeeController
{
    @Autowired
    private EmployeeRepository employeeRepository;

//    @PostMapping("/employeedata")
//    public Employee storeData(@RequestBody Employee employee)
//    {
//        return employeeRepository.save(employee);
//    }

    @GetMapping({"/showEmployees","/","/list"})
    public ModelAndView showEmployees()
    {
        ModelAndView mav = new ModelAndView("list-employees");
        List<Employee> list = employeeRepository.findAll();
        mav.addObject("employees", list);
        return mav;
    }
// handler methods
    @GetMapping("/addEmployeeForm")
    public ModelAndView addEmployeeForm()
    {
     ModelAndView mav = new ModelAndView("add-employee-form");
     Employee newEmployee = new Employee();
     mav.addObject("employee", newEmployee);
     return mav;
    }

     @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee)
     {
         employeeRepository.save(employee);
         return "redirect:/list";
     }

     @GetMapping("/showUpdateForm")
     public ModelAndView showUpdateForm(@RequestParam int employeeId)
     {
         ModelAndView mav = new ModelAndView("add-employee-form");
         Employee employee = employeeRepository.findById(employeeId).get();
         mav.addObject("employee", employee);
         return mav;
     }

     @GetMapping("/deleteEmployee")
     public String deleteEmployee(@RequestParam int employeeId)
     {
         employeeRepository.deleteById(employeeId);
         return "redirect:/list";
     }
}
