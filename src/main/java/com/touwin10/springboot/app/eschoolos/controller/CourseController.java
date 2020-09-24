package com.touwin10.springboot.app.eschoolos.controller;

import com.touwin10.springboot.app.eschoolos.model.Course;
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

@Controller
public class CourseController {
    @Autowired
    private IService<Course> courseService;

    @GetMapping(value = {"/eschoolos/dashboard/course/list", "/dashboard/course/list"})
    public ModelAndView listData(@RequestParam(defaultValue = "0") int pageno){
        ModelAndView modelAndView = new ModelAndView();
        Page<Course> pages = courseService.getAllDataPaged(pageno);
        modelAndView.addObject("courses", pages);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/course/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eschoolos/dashboard/course/new", "/dashboard/course/new"})
    public String displayNewDataForm(Model model) {
        model.addAttribute("course", new Course());
        return "secure/course/new";
    }

    @PostMapping(value = {"/eschoolos/dashboard/course/new", "/dashboard/course/new"})
    public String addNewData(@Valid @ModelAttribute("course") Course course,
                             BindingResult bindingResult, Model model) {
        ValidatorsUtils.courseValidator(course, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/course/new";
        }
        course = courseService.saveData(course);
        return "redirect:/eschoolos/dashboard/course/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/course/edit/{courseId}", "/dashboard/course/edit/{courseId}"})
    public String editData(@PathVariable Integer courseId, Model model) {
        Course course = courseService.getDataById(courseId);
        if (course != null) {
            model.addAttribute("course", course);
            return "secure/course/edit";
        }
        return "secure/course/list";
    }

    @PostMapping(value = {"/eschoolos/dashboard/course/edit", "/dashboard/course/edit"})
    public String updateData(@Valid @ModelAttribute("course") Course course,
                             BindingResult bindingResult, Model model) {
        ValidatorsUtils.courseValidator(course, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/course/edit";
        }
        course = courseService.saveData(course);
        return "redirect:/eschoolos/dashboard/course/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/course/delete/{courseId}", "/dashboard/course/delete/{courseId}"})
    public String deleteData(@PathVariable Integer courseId, Model model) {
        courseService.deleteDataById(courseId);
        return "redirect:/eschoolos/dashboard/course/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/course/search", "/dashboard/course/search"})
    public ModelAndView searchData(@RequestParam String searchString, @RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courses", courseService.searchDataPaged(searchString, 0));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/course/list");
        return modelAndView;
    }


}
