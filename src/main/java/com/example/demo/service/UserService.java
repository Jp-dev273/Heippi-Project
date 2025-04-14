package com.example.demo.service;

import com.example.demo.controller.dto.EmailUserDTO;
import com.example.demo.controller.dto.RecoveryDTO;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.controller.mapper.UserMapper;
import com.example.demo.domain.Recovery;
import com.example.demo.domain.User;
import com.example.demo.repository.RecoveryRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RecoveryRepository recoveryRepository;
    private final EmailService emailService;
    private final UserMapper userMapper;
    private final Environment environment;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RecoveryRepository recoveryRepository,
            EmailService emailService,
            UserMapper userMapper, Environment environment) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.recoveryRepository = recoveryRepository;
        this.emailService = emailService;
        this.userMapper = userMapper;
        this.environment = environment;
    }
    public void crearUsuario(UserDTO userDTO) {
        User newUser = userMapper.userDTOtoUser(userDTO);
        newUser.setActivationKey(UUID.randomUUID().toString());
        String host = "localhost";
        try{
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Host not found, using 'localhost' as fallback");
        }

        String text = "Hey, before you start, remember activate your account with the next link: "
                + host + ":" + environment.getProperty("server.port")  + "/usuarios/?key="
                + newUser.getActivationKey();
        emailService.sendSimpleMessage("DevelopApp@gmail.com", userDTO.getEmail(), "New Account registered", text);
        userRepository.save(newUser);
    }

    public List<UserDTO> findAllUsers() {
        return userMapper.usersToUsersDTOs(userRepository.findAll());
    }

    public void requestPasswordReset(EmailUserDTO emailUserDTO) {
        String email = emailUserDTO.getEmail();
        Optional<User> usuario = Optional.ofNullable(userRepository.findByEmail(email));

        if(usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        String token = UUID.randomUUID().toString();
        Recovery passwordResetToken = new Recovery();

        passwordResetToken.setUser(usuario.get());
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        recoveryRepository.save(passwordResetToken);

        String text = "Seems you forgot your password ! \n" +
                "To reset your password, copy the next token " +
                token;
        emailService.sendSimpleMessage("DevelopApp@gmail.com", email, "PasswordRecovery", text);
    }

    public void resetPassword(RecoveryDTO recoveryDTO) {

        Optional<Recovery> optionalRecovery = Optional.ofNullable(recoveryRepository.findByToken(recoveryDTO.getToken()));
        if(optionalRecovery.isEmpty()) {
            throw new RuntimeException("Recovery token no encontrado");
        }

        Recovery recoveryToken = optionalRecovery.get();

        if(recoveryToken.getExpiration().before(new Date(System.currentTimeMillis()))) {
            throw new RuntimeException("Recovery token expired");
        }

        User user = recoveryToken.getUser();
        user.setPassword(passwordEncoder.encode(recoveryDTO.getPassword()));
        userRepository.save(user);
        recoveryRepository.delete(recoveryToken);
    }

    public void activateUsuario(String key) {
        Optional<User> usuario = Optional.ofNullable(userRepository.findByActivationKey(key));
        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        User newUser = usuario.get();
        newUser.setActivated(true);
        newUser.setActivationKey(null);
        userRepository.save(newUser);
    }
}
