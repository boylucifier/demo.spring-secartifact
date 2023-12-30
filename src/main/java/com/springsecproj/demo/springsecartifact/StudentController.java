package com.springsecproj.demo.springsecartifact;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/developer")
    public String getDeveloper(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        User user = (User) auth.getPrincipal();
        System.out.println(user.getUsername());
        return "This is developer";
    }

    @GetMapping("/getStudentAddress")
    public String getStudentAddress(){
        return "My address is Patna";
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
