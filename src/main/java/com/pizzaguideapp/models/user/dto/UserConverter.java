package com.pizzaguideapp.models.user.dto;

import com.pizzaguideapp.models.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public UserIdentificationDto map(User user) {
        return new UserIdentificationDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.isEnabled()
        );
    }

    public User map(UserIdentificationDto userIdentificationDto) {
        User user = new User();
        user.setId(userIdentificationDto.getId());
        user.setUsername(userIdentificationDto.getUsername());
        user.setEmail(userIdentificationDto.getEmail());
        user.setEnabled(userIdentificationDto.isEnabled());
        return user;
    }

    public User map(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        user.setEmail(userRegisterDto.getEmail());

        return user;
    }

    public List<UserIdentificationDto> map(List<User> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
