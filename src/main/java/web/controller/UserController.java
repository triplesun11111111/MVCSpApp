package web.controller;

import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "add_user";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") int id, Model model) {
        User user = userService.getUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "edit";
    }

    @PostMapping("/users/{id}/edit")
    public String updateUser(@RequestParam("id") int id, @ModelAttribute User user) {
        userService.updateUser(user);
        return "redirect:/";
    }


    @DeleteMapping("/users/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}