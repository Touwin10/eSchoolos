package com.touwin10.springboot.app.eschoolos.controller;

import com.touwin10.springboot.app.eschoolos.model.Department;
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
public class DepartmentController {
    @Autowired
    private IService<Department> departmentService;

    @GetMapping(value = {"/eschoolos/dashboard/department/list", "/dashboard/department/list"})
    public ModelAndView listData(@RequestParam(defaultValue = "0") int pageno){
        ModelAndView modelAndView = new ModelAndView();
        Page<Department> pages = departmentService.getAllDataPaged(pageno);
        modelAndView.addObject("departments", pages);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/department/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eschoolos/dashboard/department/new", "/dashboard/department/new"})
    public String displayNewDataForm(Model model) {
        model.addAttribute("department", new Department());
        return "secure/department/new";
    }

    @PostMapping(value = {"/eschoolos/dashboard/department/new", "/dashboard/department/new"})
    public String addNewData(@Valid @ModelAttribute("department") Department department,
                             BindingResult bindingResult, Model model) {
        // ValidatorsUtils.courseValidator(department, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/department/new";
        }
        department = departmentService.saveData(department);
        return "redirect:/eschoolos/dashboard/department/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/department/edit/{departmentId}", "/dashboard/department/edit/{departmentId}"})
    public String editData(@PathVariable Integer departmentId, Model model) {
        Department department = departmentService.getDataById(departmentId);
        if (department != null) {
            model.addAttribute("department", department);
            return "secure/department/edit";
        }
        return "secure/department/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/department/profile/{departmentId}", "/dashboard/department/profile/{departmentId}"})
    public String showDataDetail(@PathVariable Integer departmentId, Model model) {
        Department department = departmentService.getDataById(departmentId);
        if (department != null) {
            model.addAttribute("department", department);
            return "secure/department/profile";
        }
        return "secure/department/list";
    }

    @PostMapping(value = {"/eschoolos/dashboard/department/edit", "/dashboard/department/edit"})
    public String updateData(@Valid @ModelAttribute("department") Department department,
                             BindingResult bindingResult, Model model) {
        // ValidatorsUtils.courseValidator(department, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/department/edit";
        }
        department = departmentService.saveData(department);
        return "redirect:/eschoolos/dashboard/department/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/department/delete/{departmentId}", "/dashboard/department/delete/{departmentId}"})
    public String deleteData(@PathVariable Integer departmentId, Model model) {
        departmentService.deleteDataById(departmentId);
        return "redirect:/eschoolos/dashboard/department/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/department/search", "/dashboard/department/search"})
    public ModelAndView searchData(@RequestParam String searchString, @RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("departments", departmentService.searchDataPaged(searchString, 0));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/department/list");
        return modelAndView;
    }

}
