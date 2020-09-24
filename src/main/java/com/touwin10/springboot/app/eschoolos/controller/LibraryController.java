package com.touwin10.springboot.app.eschoolos.controller;

import com.touwin10.springboot.app.eschoolos.model.Asset;
import com.touwin10.springboot.app.eschoolos.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LibraryController {
    @Autowired
    private IService<Asset> assetService;

    @GetMapping(value = {"/eschoolos/dashboard/library/list", "/dashboard/library/list"})
    public ModelAndView listData(@RequestParam(defaultValue = "0") int pageno){
        ModelAndView modelAndView = new ModelAndView();
        Page<Asset> pages = assetService.getAllDataPaged(pageno);
        modelAndView.addObject("assets", pages);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/library/list");
        return modelAndView;
    }

    @GetMapping(value = {"/eschoolos/dashboard/library/new", "/dashboard/library/new"})
    public String displayNewDataForm(Model model) {
        model.addAttribute("asset", new Asset());
        return "secure/library/new";
    }

    @PostMapping(value = {"/eschoolos/dashboard/library/new", "/dashboard/library/new"})
    public String addNewData(@Valid @ModelAttribute("asset") Asset asset,
                             BindingResult bindingResult, Model model) {
        // ValidatorsUtils.courseValidator(asset, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/library/new";
        }
        asset = assetService.saveData(asset);
        return "redirect:/eschoolos/dashboard/library/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/library/edit/{assetId}", "/dashboard/library/edit/{assetId}"})
    public String editData(@PathVariable Integer assetId, Model model) {
        Asset asset = assetService.getDataById(assetId);
        if (asset != null) {
            model.addAttribute("asset", asset);
            return "secure/library/edit";
        }
        return "secure/library/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/library/profile/{assetId}", "/dashboard/library/profile/{assetId}"})
    public String showDataDetail(@PathVariable Integer assetId, Model model) {
        Asset asset = assetService.getDataById(assetId);
        if (asset != null) {
            model.addAttribute("asset", asset);
            return "secure/library/profile";
        }
        return "secure/library/list";
    }

    @PostMapping(value = {"/eschoolos/dashboard/library/edit", "/dashboard/library/edit"})
    public String updateData(@Valid @ModelAttribute("asset") Asset asset,
                             BindingResult bindingResult, Model model) {
        // ValidatorsUtils.courseValidator(library, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secure/library/edit";
        }
        asset = assetService.saveData(asset);
        return "redirect:/eschoolos/dashboard/library/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/library/delete/{assetId}", "/dashboard/library/delete/{assetId}"})
    public String deleteData(@PathVariable Integer assetId, Model model) {
        assetService.deleteDataById(assetId);
        return "redirect:/eschoolos/dashboard/library/list";
    }

    @GetMapping(value = {"/eschoolos/dashboard/library/search", "/dashboard/library/search"})
    public ModelAndView searchData(@RequestParam String searchString, @RequestParam(defaultValue = "0") int pageno) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("assets", assetService.searchDataPaged(searchString, 0));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageno);
        modelAndView.setViewName("secure/library/list");
        return modelAndView;
    }

}