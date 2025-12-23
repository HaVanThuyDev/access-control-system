package com.example.employee.service.impl;

import com.example.employee.model.dto.UserDTO;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.model.entity.User;
import com.example.employee.repository.UserRepository;
import com.example.employee.service.JwtService;
import com.example.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public  UserReponseDTO login(String gmail, String password) {
        User user = userRepository.findByGmail(gmail).orElseThrow(() -> new BadCredentialsException("Invalid gmail or password"));
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Invalid password");
        }
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getGmail(),
                user.getPassword(),
                user.getRole()
        );
        String token = jwtService.generateToken(userDTO);
        return new UserReponseDTO(user.getName(),user.getRole(),token);
    }
    @Override
    public UserReponseDTO register (UserRequestDTO request) {
       if (userRepository.existsByGmail(request.getGmail())) {
           throw new BadCredentialsException("User already exists");
       }
       User User = new User();
       User.setGmail(request.getGmail());
       User.setName(request.getName());
       User.setPhone(request.getPhone());
       User.setRole(request.getRole());
       User.setPassword(request.getPass());
       userRepository.save(User );
       return new UserReponseDTO(User.getGmail(),User.getRole(),null);

    }
}

