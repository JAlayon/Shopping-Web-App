package com.alayon.shopme.site.admin.user;

import com.alayon.shopme.common.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAll(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
}
