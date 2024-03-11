package com.springjwt.jwt.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springjwt.jwt.entity.CustomUserDetails;
import com.springjwt.jwt.entity.User;
import com.springjwt.jwt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.userRepository.findByUsername(username);

    if (user == null) {
      logger.error("username Not found" + username);
      throw new UsernameNotFoundException("could not find user");
    }

    logger.info("username found" + username);
    return new CustomUserDetails(user);
  }
  
}
