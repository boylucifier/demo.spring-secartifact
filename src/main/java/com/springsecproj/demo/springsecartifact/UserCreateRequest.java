package com.springsecproj.demo.springsecartifact;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private Boolean isDeveloper;
    @org.springframework.beans.factory.annotation.Value("${user.authority.developer}")
    private String developerAuthority;
    @org.springframework.beans.factory.annotation.Value("${user.authority.devops}")
    private String devopsAuthority;
    public MyUser to(){
        return MyUser.builder().userName(userName).password(passwordEncoder.encode(password))
                .authorities(isDeveloper?developerAuthority:devopsAuthority).build();
    }

}
