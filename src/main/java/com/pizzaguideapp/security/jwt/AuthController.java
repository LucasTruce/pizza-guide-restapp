package com.pizzaguideapp.security.jwt;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.user.User;
import com.pizzaguideapp.models.user.UserRepository;
import com.pizzaguideapp.models.user.UserService;
import com.pizzaguideapp.models.user.dto.UserIdentificationDto;
import com.pizzaguideapp.models.user.dto.UserLoginDto;
import com.pizzaguideapp.models.user.dto.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider jwtUtils;
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody @Valid UserLoginDto userLoginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserIdentificationDto> registerUser(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        if(userRepository.existsByUsername(userRegisterDto.getUsername())){
            throw new EntityNotFoundException("User already exists!");
        }
        return new ResponseEntity<>(userService.saveUser(userRegisterDto), HttpStatus.OK);

    }


}
