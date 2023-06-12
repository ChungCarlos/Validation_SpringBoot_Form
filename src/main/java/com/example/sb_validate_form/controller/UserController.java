package com.example.sb_validate_form.controller;

import com.example.sb_validate_form.model.User;
import com.example.sb_validate_form.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/create-user")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/views/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @PostMapping("/create-user")
    public ModelAndView saveProduct(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/views/create");
        }
        iUserService.save(user);
        ModelAndView modelAndView = new ModelAndView("/views/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message", "New user created successfully");
        return modelAndView;
    }
    @GetMapping("/user")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView("/views/list");
        modelAndView.addObject("users", iUserService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<User> user = iUserService.findById(id);
        if (user.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/views/edit");
            modelAndView.addObject("user", user.get());
            return modelAndView;
        } else {
            return new ModelAndView("/views/error-404");
        }
    }

    @PostMapping("/edit-user")
    public ModelAndView updateCustomer(@Validated @ModelAttribute("user") User user) {
        iUserService.save(user);
        ModelAndView modelAndView = new ModelAndView("/views/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "Product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<User> user = iUserService.findById(id);
        if (user.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/views/delete");
            modelAndView.addObject("users", user.get());
            return modelAndView;

        } else {
            return new ModelAndView("/views/error-404");
        }
    }

    @PostMapping("/delete-user")
    public ModelAndView deleteCustomer(@ModelAttribute("user") User user) {
        iUserService.remove(user.getId());
        ModelAndView modelAndView = new ModelAndView("/views/list");
        modelAndView.addObject("users" , iUserService.findAll());
        return modelAndView;
    }
}
