package com.example.demo.controller.mapper;

import com.example.demo.controller.dto.UserDTO;
import com.example.demo.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public User userDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setTelephone(userDTO.getTelephone());
        user.setAuthority(userDTO.getAuthority());
        return user;
    }
    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setTelephone(user.getTelephone());
        userDTO.setAuthority(user.getAuthority());
        return userDTO;
    }

    public List<UserDTO> usersToUsersDTOs(Iterable<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(userToUserDTO(user));
        }
        return userDTOS;
    }
}
