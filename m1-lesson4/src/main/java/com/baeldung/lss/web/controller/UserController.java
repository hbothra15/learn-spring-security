package com.baeldung.lss.web.controller;

import com.baeldung.lss.persistence.UserRepository;
import com.baeldung.lss.web.model.User;

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
@RequestMapping("/")
public class UserController {

    private static final String USER_FORM = "users/form";
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // API

    @GetMapping
    public ModelAndView list() {
        Iterable<User> users = this.userRepository.findAll();
        return new ModelAndView("users/list", "users", users);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") User user) {
        return new ModelAndView("users/view", "user", user);
    }

    @PostMapping
    public ModelAndView create(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return new ModelAndView(USER_FORM, "formErrors", result.getAllErrors());
        }
        user = this.userRepository.save(user);
        redirect.addFlashAttribute("globalMessage", "Successfully created a new user");
        return new ModelAndView("redirect:/{user.id}", "user.id", user.getId());
    }

    @GetMapping(value = "delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        this.userRepository.deleteUser(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") User user) {
        return new ModelAndView(USER_FORM, "user", user);
    }

    // the form

    @GetMapping(params = "form")
    public String createForm(@ModelAttribute User user) {
        return USER_FORM;
    }

}
