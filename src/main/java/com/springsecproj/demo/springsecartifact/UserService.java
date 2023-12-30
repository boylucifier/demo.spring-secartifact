package com.springsecproj.demo.springsecartifact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepos userRepos;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepos.findByUserName(username);
        if(null==user)
            throw new UsernameNotFoundException("user not found by this name");
        return user;
    }

    public void saveUser(UserCreateRequest userCreateRequest) {
        userRepos.save(userCreateRequest.to());
    }
}
