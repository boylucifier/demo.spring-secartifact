package com.springsecproj.demo.springsecartifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/developer")
    public String getDeveloper(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        User user = (User) auth.getPrincipal();
        System.out.println(user.getUsername());
        return "This is developer";
    }

    @PostMapping("/signup")
    public void addUser(@Valid @RequestBody UserCreateRequest userCreateRequest){
        userService.saveUser(userCreateRequest);
    }

    @GetMapping("/devops")
    public String getDevops(){
        return "this is devops";
    }

    @GetMapping("/tester")
    public String getTester(){
        return "this is tester";
    }
    @GetMapping("/projectManager")
    public String getProjectManager(){
        return  "this is project manager";
    }

    @PostMapping("/sre")
    public String getSre(){
        return "this is sre";
    }
}
