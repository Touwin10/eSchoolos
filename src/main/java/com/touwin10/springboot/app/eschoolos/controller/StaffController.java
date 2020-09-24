package com.touwin10.springboot.app.eschoolos.controller;

import com.touwin10.springboot.app.eschoolos.model.Professor;
import com.touwin10.springboot.app.eschoolos.model.Staff;
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
public class StaffController {

    @Autowired
    private IService<Staff> staffService;

    @GetMapping(value = {"/eschoolos/dashboard/staff/list", "/dashboard/staff/list"})
    public ModelAndView listData(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Staff> pages = staffService.getAllDataPaged(pageno);
        modelAndView.addObject("staffs", pages);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/staff/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eschoolos/dashboard/staff/new", "/dashboard/staff/new"})
    public String displayNewDataForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "secure/staff/new";
    }

    @PostMapping(value = {"/eschoolos/dashboard/staff/new", "/dashboard/staff/new"})
    public String addNewData(@Valid @ModelAttribute("staff") Staff staff,
                             BindingResult bindingResult, Model model) {
        // ValidatorsUtils.staffValidator(staff, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/staff/new";
        }
        staff = staffService.saveData(staff);
        return "redirect:/eschoolos/dashboard/staff/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/staff/edit/{staffId}", "/dashboard/staff/edit/{staffId}"})
    public String editData(@PathVariable Integer staffId, Model model) {
        Staff staff = staffService.getDataById(staffId);
        if (staff != null) {
            model.addAttribute("staff", staff);
            return "secure/staff/edit";
        }
        return "secure/staff/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/staff/profile"})
    public String showProfile(){
        return "secure/staff/profile";
    }

    @GetMapping(value = {"/eschoolos/dashboard/staff/profile/{staffId}", "/dashboard/staff/profile/{staffId}"})
    public String showDataDetail(@PathVariable Integer staffId, Model model) {
        Staff staff = staffService.getDataById(staffId);
        if (staff != null) {
            model.addAttribute("staff", staff);
            return "secure/staff/profile";
        }
        return "secure/staff/list";
    }

    @PostMapping(value = {"/eschoolos/dashboard/staff/edit", "/dashboard/staff/edit"})
    public String updateData(@Valid @ModelAttribute("staff") Staff staff,
                             BindingResult bindingResult, Model model) {
        // ValidatorsUtils.staffValidator(staff, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/staff/edit";
        }
        staff = staffService.saveData(staff);
        return "redirect:/eschoolos/dashboard/staff/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/staff/delete/{staffId}", "/dashboard/staff/delete/{staffId}"})
    public String deleteData(@PathVariable Integer staffId, Model model) {
        staffService.deleteDataById(staffId);
        return "redirect:/eschoolos/dashboard/staff/list";
    }


    @GetMapping(value = {"/eschoolos/dashboard/staff/search", "/dashboard/staff/search"})
    public ModelAndView searchData(@RequestParam String searchString, @RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("staffs", staffService.searchDataPaged(searchString, 0));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/staff/list");
        return modelAndView;
    }



}
