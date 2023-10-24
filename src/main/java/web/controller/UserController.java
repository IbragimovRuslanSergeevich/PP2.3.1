package web.controller;

import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String show(Model model) {
        model.addAttribute("users", userService.getUsersList());
        return "users";
    }

    @GetMapping(value = "/users", params = "id")
    public String showIdUser(@RequestParam(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "id_user";
    }


    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") @Valid User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam(value = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/edit")
    public String editUserForm(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/users/edit{id}")
    public String editUser(@ModelAttribute("user") @Valid User user) {
        userService.editUser(user);
        return "redirect:/users";
    }
}
