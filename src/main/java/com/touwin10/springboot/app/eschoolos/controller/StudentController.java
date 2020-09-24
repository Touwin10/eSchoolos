package com.touwin10.springboot.app.eschoolos.controller;

import com.touwin10.springboot.app.eschoolos.model.Student;
import com.touwin10.springboot.app.eschoolos.service.IService;
import com.touwin10.springboot.app.eschoolos.validators.ValidatorsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private IService<Student> studentService;

    @GetMapping(value = {"/eschoolos/dashboard/student/list", "/dashboard/student/list"})
    public ModelAndView listData(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Student> pages = studentService.getAllDataPaged(pageno);
        modelAndView.addObject("students", pages);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/student/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eschoolos/dashboard/student/new", "/dashboard/student/new"})
    public String displayNewDataForm(Model model) {
        model.addAttribute("student", new Student());
        return "secure/student/new";
    }

    @PostMapping(value = {"/eschoolos/dashboard/student/new", "/dashboard/student/new"})
    public String addNewData(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        ValidatorsUtils.studentValidator(student, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/student/new";
        }
        student = studentService.saveData(student);
        return "redirect:/eschoolos/dashboard/student/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/student/edit/{studentId}", "/dashboard/student/edit/{studentId}"})
    public String editData(@PathVariable Integer studentId, Model model) {
        Student student = studentService.getDataById(studentId);
        if (student != null) {
            model.addAttribute("student", student);
            return "secure/student/edit";
        }
        return "secure/student/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/student/profile"})
    public String showProfile(){
        return "secure/student/profile";
    }

    @GetMapping(value = {"/eschoolos/dashboard/student/profile/{studentId}",
            "/dashboard/student/profile/{studentId}"})
    public String showDataDetail(@PathVariable Integer studentId, Model model) {
        Student student = studentService.getDataById(studentId);
        if (student != null) {
            model.addAttribute("student", student);
            return "secure/student/profile";
        }
        return "secure/student/list";
    }

    @PostMapping(value = {"/eschoolos/dashboard/student/edit", "/dashboard/student/edit"})
    public String updateData(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        ValidatorsUtils.studentValidator(student, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/student/edit";
        }
        student = studentService.saveData(student);
        return "redirect:/eschoolos/dashboard/student/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/student/delete/{studentId}", "/dashboard/student/delete/{studentId}"})
    public String deleteData(@PathVariable Integer studentId, Model model) {
        studentService.deleteDataById(studentId);
        return "redirect:/eschoolos/dashboard/student/list";
    }


    @GetMapping(value = {"/eschoolos/dashboard/student/search", "/dashboard/student/search"})
    public ModelAndView searchData(@RequestParam String searchString, @RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.searchDataPaged(searchString, 0));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/student/list");
        return modelAndView;
    }

}
