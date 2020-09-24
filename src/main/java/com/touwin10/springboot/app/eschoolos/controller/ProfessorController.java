package com.touwin10.springboot.app.eschoolos.controller;

import com.touwin10.springboot.app.eschoolos.model.Professor;
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
public class ProfessorController {

    @Autowired
    private IService<Professor> professorService;

    @GetMapping(value = {"/eschoolos/dashboard/professor/list", "/dashboard/professor/list"})
    public ModelAndView listData(@RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Professor> pages = professorService.getAllDataPaged(pageno);
        modelAndView.addObject("professors", pages);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/professor/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eschoolos/dashboard/professor/new", "/dashboard/professor/new"})
    public String displayNewDataForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "secure/professor/new";
    }

    @PostMapping(value = {"/eschoolos/dashboard/professor/new", "/dashboard/professor/new"})
    public String addNewData(@Valid @ModelAttribute("professor") Professor professor,
                             BindingResult bindingResult, Model model) {
        ValidatorsUtils.professorValidator(professor, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/professor/new";
        }
        professor = professorService.saveData(professor);
        return "redirect:/eschoolos/dashboard/professor/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/professor/edit/{professorId}", "/dashboard/professor/edit/{professorId}"})
    public String editData(@PathVariable Integer professorId, Model model) {
        Professor professor = professorService.getDataById(professorId);
        if (professor != null) {
            model.addAttribute("professor", professor);
            return "secure/professor/edit";
        }
        return "secure/professor/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/professor/profile"})
    public String showProfile(){
        return "secure/professor/profile";
    }

    @GetMapping(value = {"/eschoolos/dashboard/professor/profile/{professorId}", "/dashboard/professor/profile/{professorId}"})
    public String showDataDetail(@PathVariable Integer professorId, Model model) {
        Professor professor = professorService.getDataById(professorId);
        if (professor != null) {
            model.addAttribute("professor", professor);
            return "secure/professor/profile";
        }
        return "secure/professor/list";
    }

    @PostMapping(value = {"/eschoolos/dashboard/professor/edit", "/dashboard/professor/edit"})
    public String updateData(@Valid @ModelAttribute("professor") Professor professor,
                             BindingResult bindingResult, Model model) {
        ValidatorsUtils.professorValidator(professor, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/professor/edit";
        }
        professor = professorService.saveData(professor);
        return "redirect:/eschoolos/dashboard/professor/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/professor/delete/{professorId}", "/dashboard/professor/delete/{professorId}"})
    public String deleteData(@PathVariable Integer professorId, Model model) {
        professorService.deleteDataById(professorId);
        return "redirect:/eschoolos/dashboard/professor/list";
    }


    @GetMapping(value = {"/eschoolos/dashboard/professor/search", "/dashboard/professor/search"})
    public ModelAndView searchData(@RequestParam String searchString, @RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("professors", professorService.searchDataPaged(searchString, 0));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/professor/list");
        return modelAndView;
    }


}
