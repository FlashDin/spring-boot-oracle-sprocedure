package com.flashdin.springbootoracle.controller;

import com.flashdin.springbootoracle.entity.User;
import com.flashdin.springbootoracle.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserSevice userService;

    @GetMapping(path = "/create")
    public String viewCreate(Model model) {
        model.addAttribute("dataSets", new User());
        return "/user/create";
    }

    @GetMapping(path = "/update/{id}")
    public String viewUpdate(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("dataSets", userService.findById(id));
        return "/user/update";
    }

    @PostMapping(value = "/create")
    public String save(User param) {
        User data = userService.save(param);
        if (data.getId() == 0) {
            return "redirect:/user/create?failed";
        } else {
            return "redirect:/user/create?success";
        }
    }

    @PutMapping(path = "/update")
    public String update(User param) {
        User data = userService.update(param);
        if (data.getId() == 0) {
            return "redirect:/user?ufailed";
        } else {
            return "redirect:/user?usuccess";
        }
    }

    @DeleteMapping(path = "/delete")
    public String delete(User param) {
        int data = userService.delete(param);
        if (data == 0) {
            return "redirect:/user?dfailed";
        } else {
            return "redirect:/user?dsuccess";
        }
    }

    @GetMapping(path = "")
    public String viewData(Model model, @RequestParam(value = "search", required = false) String param,
                           @RequestParam(value = "filter", required = false) String param1) {
        if (param == null && param1 == null) {
            model.addAttribute("dataSets", userService.findAll());
        } else {
            User user = new User();
            user.setUsername(param);
            model.addAttribute("dataSets", userService.findByUsername(user));
        }
        return "/user/list";
    }

}
