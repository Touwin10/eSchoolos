package com.touwin10.springboot.app.eschoolos.controller;


import com.touwin10.springboot.app.eschoolos.model.Course;
import com.touwin10.springboot.app.eschoolos.model.Professor;
import com.touwin10.springboot.app.eschoolos.model.Student;
import com.touwin10.springboot.app.eschoolos.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    @Autowired
    private IService<Student> studentService;
    @Autowired
    private IService<Professor> professorService;
    @Autowired
    private IService<Course> courseService;

    @GetMapping(value = {"/eschoolos/dashboard"})
    public String showHomepage() {
        return "secure/dashboard/index";
    }

    @GetMapping(value = {"/eschoolos/dashboard/reports", "/dashboard/reports"})
    public ModelAndView getReports() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentSize", studentService.getAllData().size());
        modelAndView.addObject("professorSize", professorService.getAllData().size());
        modelAndView.addObject("courseSize", courseService.getAllData().size());
        modelAndView.setViewName("secure/dashboard/index");
        return modelAndView;
    }
}
