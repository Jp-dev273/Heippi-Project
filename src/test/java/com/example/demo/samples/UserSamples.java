package com.example.demo.samples;

import com.example.demo.config.Authorities;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.domain.User;

public class UserSamples {
    private final String
            idTest1 = "1111",
            emailTest1 = "test1@test.com",
            passwordTest1 = "123456",
            telephoneTest1 = "123456789";
    private final Authorities authorityTest1 = Authorities.HOSPITAL;

    private final String
            idTest2 = "2222",
            emailTest2 = "test2@test.com",
            passwordTest2 = "1234567",
            telephoneTest2 = "1123456789";
    private final Authorities authorityTest2 = Authorities.HOSPITAL;

    public UserDTO userDTOTest1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(idTest1);
        userDTO.setEmail(emailTest1);
        userDTO.setPassword(passwordTest1);
        userDTO.setAuthority(authorityTest1);
        return userDTO;
    }

    public UserDTO userDTOTest2() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(idTest2);
        userDTO.setEmail(emailTest2);
        userDTO.setPassword(passwordTest2);
        userDTO.setAuthority(authorityTest2);
        return userDTO;
    }

    public User userTest1() {
        User user = new User();
        user.setId(idTest1);
        user.setEmail(emailTest1);
        user.setPassword(passwordTest1);
        user.setAuthority(authorityTest1);
        return user;
    }

    public User userTest2() {
        User user = new User();
        user.setId(idTest2);
        user.setEmail(emailTest2);
        user.setPassword(passwordTest2);
        user.setAuthority(authorityTest2);
        return user;
    }
}
