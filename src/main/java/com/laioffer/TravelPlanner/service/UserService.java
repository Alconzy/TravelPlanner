package com.laioffer.TravelPlanner.service;

import com.laioffer.TravelPlanner.entity.Role;
import com.laioffer.TravelPlanner.entity.User;
import com.laioffer.TravelPlanner.repository.RoleRepository;
import com.laioffer.TravelPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role role = new Role();
        role.setEmail(user.getEmail());
        role.setRole("ADMIN");
        userRepository.save(user);
        roleRepository.save(role);
    }


}
