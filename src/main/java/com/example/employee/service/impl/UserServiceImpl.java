package com.example.employee.service.impl;

import com.example.employee.model.dto.UserDTO;
import com.example.employee.model.dto.reponse.LoginResponseDTO;
import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.model.entity.User;
import com.example.employee.model.enums.Action;
import com.example.employee.model.enums.ResourceType;
import com.example.employee.model.enums.Role;
import com.example.employee.repository.RolePermissionScopeRepository;
import com.example.employee.repository.UserRepository;
import com.example.employee.security.JwtProvider;
import com.example.employee.security.annotation.PermissionCheck;
import com.example.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.employee.model.enums.Action.DELETE;
import static com.example.employee.model.enums.Action.SEARCH;
import static com.example.employee.model.enums.ResourceType.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RolePermissionScopeRepository rolePermissionScopeRepository;

    @Override
    public LoginResponseDTO login(String gmail, String password) {
        User user = userRepository.findByGmail(gmail).orElseThrow(() -> new BadCredentialsException("Invalid gmail or password"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid gmail or password");
        }
        if (user.getRole() == null) {
            throw new IllegalStateException("User has no role assigned");
        }
        Role role=user.getRole();
        List<String> permissions = rolePermissionScopeRepository.findPermissionsByRole(role).stream().map(p -> p.getResource().trim() + "." + p.getAction().trim() + ":" + p.getScope().name().trim()).distinct().toList();

        String token = jwtProvider.generateToken(user.getId(), user.getGmail(), role, permissions);
            return new LoginResponseDTO(
                user.getName(),
                user.getRole(),
                token
        );
    }
    @Override
    public User register(UserRequestDTO request) {

        if (userRepository.findByGmail(request.getGmail()).isPresent()) {
            throw new BadCredentialsException("User already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setGmail(request.getGmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.EMPLOYEE);
        return userRepository.save(user);
    }
    @Override
    @PermissionCheck(resource = USER, action = SEARCH)
    public List<UserReponseDTO> getAll() {
        long total =userRepository.count();
        return userRepository.findAll().stream().map(user -> {
            UserReponseDTO list = new UserReponseDTO(user);
            list.setTotal(total);
            return list;
        }).toList();
    }
    @Override
    @PermissionCheck(resource = USER, action = DELETE)
    public List<UserDTO> delete(Long id) {
       User user = userRepository.findById(id).orElseThrow(() -> new BadCredentialsException("User does not exist"));
       userRepository.delete(user);
       return new ArrayList<>();
    }

    @Override
    @PermissionCheck(resource = USER, action = SEARCH)
    public List<UserReponseDTO> search(String name, String email) {
        List<User> users = userRepository.searchBy(name,email);
        return  users.stream().map(UserReponseDTO :: new).toList();
    }

}
