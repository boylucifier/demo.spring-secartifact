package com.springsecproj.demo.springsecartifact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


public interface UserRepos extends JpaRepository<MyUser,Integer> {
    MyUser findByUserName(String username);
}
