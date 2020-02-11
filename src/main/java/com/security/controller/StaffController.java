package com.security.controller;

import com.security.model.Staff;
import com.security.service.SecurityService;
import com.security.service.StaffService;
import com.security.validator.StaffValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private StaffValidator staffValidator;

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("staffForm", new Staff());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute("staffForm") Staff staffForm,
                               BindingResult bindingResult, Model model) {
        staffValidator.validate(staffForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        staffService.save(staffForm);

        securityService.autoLogin(staffForm.getLogin(), staffForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping(value = {"/", "welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}
