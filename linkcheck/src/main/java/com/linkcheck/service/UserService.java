package com.linkcheck.service;

import com.linkcheck.model.User;
import com.linkcheck.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void saveUser(User user) {
        if (user.getEmail() == null) {
            System.out.println("email cannot be null");
        } else {
            userRepository.save(user);
        }

    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }


}
