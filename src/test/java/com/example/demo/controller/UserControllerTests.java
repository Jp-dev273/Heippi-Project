package com.example.demo.controller;

import com.example.demo.controller.dto.EmailUserDTO;
import com.example.demo.controller.dto.RecoveryDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.domain.Recovery;
import com.example.demo.domain.User;
import com.example.demo.repository.RecoveryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.samples.UserSamples;
import com.example.demo.service.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserControllerTests {
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RecoveryRepository recoveryRepository;
    @Mock
    private EmailService emailService;
    private final UserSamples userSamples = new UserSamples();

    @Test
    @DisplayName("Should create an Usuario and return OK")
    void createUser() {
        UserDTO userDTO = userSamples.userDTOTest1();

        ResponseEntity<Void> response = userController.postUser(userDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
        assertNotNull(user);
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertTrue(passwordEncoder.matches(userDTO.getPassword(), user.getPassword()));
        assertEquals(userDTO.getAuthority(), user.getAuthority());
    }

    @Test
    @DisplayName("Should return all Users registered and return OK")
    void getAllUsers() {
        User user1 = new UserSamples().userTest1();
        User user2 = new UserSamples().userTest2();

        userRepository.save(user1);
        userRepository.save(user2);

        ResponseEntity<List<UserDTO>> response = userController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<UserDTO> users = response.getBody();
        assertNotNull(users);
        assertEquals(2 , users.size());

        UserDTO firstUser = users.get(0);
        UserDTO lastUser = users.get(1);

        assertNotNull(firstUser);
        assertEquals(user1.getId(), firstUser.getId());
        assertEquals(user1.getEmail(), firstUser.getEmail());
        assertNull(firstUser.getPassword());
        assertEquals(user1.getAuthority(), firstUser.getAuthority());

        assertNotNull(lastUser);
        assertEquals(user2.getId(), lastUser.getId());
        assertEquals(user2.getEmail(), lastUser.getEmail());
        assertNull(lastUser.getPassword());
        assertEquals(user2.getAuthority(), lastUser.getAuthority());
    }

    @Test
    @DisplayName("Should activate an User after a creation and return OK")
    void activateUser() {

        UserDTO userDTO = new UserSamples().userDTOTest1();

        userController.postUser(userDTO);

        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        assertTrue(optionalUser.isPresent());

        User savedUser = optionalUser.get();
        assertNotNull(savedUser);
        assertEquals(userDTO.getEmail(), savedUser.getEmail());
        assertTrue(passwordEncoder.matches(userDTO.getPassword(), savedUser.getPassword()));
        assertEquals(userDTO.getAuthority(), savedUser.getAuthority());

        ResponseEntity<Void> response = userController.activateUser(savedUser.getActivationKey());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Optional<User> optionalActivatedUser = userRepository.findById(savedUser.getId());
        assertTrue(optionalActivatedUser.isPresent());
        User activatedUser = optionalActivatedUser.get();
        assertNotNull(activatedUser);
        assertEquals(userDTO.getEmail(), activatedUser.getEmail());
        assertTrue(passwordEncoder.matches(userDTO.getPassword(), activatedUser.getPassword()));
        assertEquals(userDTO.getAuthority(), activatedUser.getAuthority());
        assertTrue(savedUser.isActivated());
    }

    @Test
    @DisplayName("Should request a recovery process and update password")
    void requestRecoveryAndUpdatePassword() {
        EmailUserDTO emailUserDTO = new EmailUserDTO();
        User user = new UserSamples().userTest1();

        userRepository.save(user);

        emailUserDTO.setEmail(user.getEmail());

        ResponseEntity<Void> requestRecovery = userController.requestRecovery(emailUserDTO);
        assertEquals(HttpStatus.ACCEPTED, requestRecovery.getStatusCode());

        Recovery recoveryById = recoveryRepository.findById(user.getId()).orElse(null);
        assertNotNull(recoveryById);
        assertNotNull(recoveryById.getToken());

        RecoveryDTO recoveryDTO = new RecoveryDTO();
        recoveryDTO.setToken(recoveryById.getToken());
        String newPassword = "newPassword";
        recoveryDTO.setPassword(newPassword);

        ResponseEntity<Void> responseUpdatePassword = userController.updatePassword(recoveryDTO);
        assertEquals(HttpStatus.NO_CONTENT, responseUpdatePassword.getStatusCode());

        Optional<User> optionalUser = userRepository.findById(user.getId());
        assertTrue(optionalUser.isPresent());
        User userUpdated = optionalUser.get();
        assertNotNull(userUpdated);
        assertEquals(user.getEmail(), userUpdated.getEmail());
        assertTrue(passwordEncoder.matches(newPassword, userUpdated.getPassword()));
        assertEquals(user.getAuthority(), userUpdated.getAuthority());
        assertEquals(user.isActivated(), userUpdated.isActivated());
    }
}
