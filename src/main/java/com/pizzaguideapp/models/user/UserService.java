package com.pizzaguideapp.models.user;


import com.pizzaguideapp.models.role.RoleRepository;
import com.pizzaguideapp.models.user.dto.UserConverter;
import com.pizzaguideapp.models.user.dto.UserIdentificationDto;
import com.pizzaguideapp.models.user.dto.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder pass, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userConverter = new UserConverter();
        this.passwordEncoder = pass;
        this.roleRepository = roleRepository;
    }

    public UserIdentificationDto saveUser(@Valid UserRegisterDto userRegisterDto) {
        User user = userConverter.map(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(Collections.singletonList(roleRepository.getOne(2L)));
        user.setEnabled(true);

        return userConverter.map(userRepository.save(user));
    }

    public List<UserIdentificationDto> getUsers() {
        return userConverter.map(userRepository.findAll());
    }

    public boolean userExists(String login){
        return userRepository.existsByUsername(login);
    }

    public Optional<User> findUserByLogin(String login){
        return userRepository.findUserByUsername(login);
    }

}
