package com.flashdin.springbootoracle.controller;

import com.flashdin.springbootoracle.entity.Profile;
import com.flashdin.springbootoracle.service.ProfileSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileSevice profileService;

    @GetMapping(path = "/create")
    public String viewCreate(Model model) {
        model.addAttribute("dataSets", new Profile());
        return "/profile/create";
    }

    @GetMapping(path = "/update/{id}")
    public String viewUpdate(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("dataSets", profileService.findById(id));
        return "/profile/update";
    }

    @PostMapping(value = "/create")
    public String save(Profile param) {
        Profile data = profileService.save(param);
        if (data.getId() == 0) {
            return "redirect:/profile/create?failed";
        } else {
            return "redirect:/profile/create?success";
        }
    }

    @PutMapping(path = "/update")
    public String update(Profile param) {
        Profile data = profileService.update(param);
        if (data.getId() == 0) {
            return "redirect:/profile?ufailed";
        } else {
            return "redirect:/profile?usuccess";
        }
    }

    @DeleteMapping(path = "/delete")
    public String delete(Profile param) {
        int data = profileService.delete(param);
        if (data == 0) {
            return "redirect:/profile?dfailed";
        } else {
            return "redirect:/profile?dsuccess";
        }
    }

    @GetMapping(path = "")
    public String viewData(Model model, @RequestParam(value = "search", required = false) String param,
                           @RequestParam(value = "filter", required = false) String param1) {
        if (param == null && param1 == null) {
            model.addAttribute("dataSets", profileService.findAll());
        } else {
            Profile profile = new Profile();
            profile.setNamaLengkap(param);
            model.addAttribute("dataSets", profileService.findByName(profile));
        }
        return "/profile/list";
    }

}
