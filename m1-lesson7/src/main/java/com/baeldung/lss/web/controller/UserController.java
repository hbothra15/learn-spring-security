package com.baeldung.lss.web.controller;

import com.baeldung.lss.persistence.UserRepository;
import com.baeldung.lss.web.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String TL_FORM = "tl/form";

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView list() {
        Iterable<User> users = this.userRepository.findAll();
        return new ModelAndView("tl/list", "users", users);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") User user) {
        return new ModelAndView("tl/view", "user", user);
    }

    @PostMapping
    public ModelAndView create(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView(TL_FORM, "formErrors", result.getAllErrors());
        }
        user = this.userRepository.save(user);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new user");
        return new ModelAndView("redirect:/user/{user.id}", "user.id", user.getId());
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.userRepository.findById(id)
                           .ifPresent(user -> this.userRepository.delete(user));
        return new ModelAndView("redirect:/user/");
    }

    @GetMapping("modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") User user) {
        return new ModelAndView(TL_FORM, "user", user);
    }

    // the form

    @GetMapping(params = "form")
    public String createForm(@ModelAttribute User user) {
        return TL_FORM;
    }

}
