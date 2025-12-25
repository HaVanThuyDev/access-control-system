package com.example.employee.service.impl;

import com.example.employee.model.dto.reponse.UserReponseDTO;
import com.example.employee.model.dto.request.UserRequestDTO;
import com.example.employee.model.entity.User;
import com.example.employee.model.enums.Action;
import com.example.employee.model.enums.ResourceType;
import com.example.employee.model.enums.Role;
import com.example.employee.repository.RolePermissionScopeRepository;
import com.example.employee.repository.UserDeviceRepository;
import com.example.employee.repository.UserRepository;
import com.example.employee.security.JwtProvider;
import com.example.employee.security.annotation.PermissionCheck;
import com.example.employee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RolePermissionScopeRepository rolePermissionScopeRepository;
    private final UserDeviceRepository userDeviceRepository;

    @Override
    public UserReponseDTO login(String gmail, String password) {
        User user = userRepository.findByGmail(gmail).orElseThrow(() -> new BadCredentialsException("Invalid gmail or password"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid gmail or password");
        }
        if (user.getRole() == null) {
            throw new IllegalStateException("User has no role assigned");
        }
        String roleName = user.getRole().name();
        Role role=user.getRole();
        List<String> permissions = rolePermissionScopeRepository.findPermissionsByRole(role).stream().map(p -> p.getResource().trim() + "." + p.getAction().trim() + ":" + p.getScope().name().trim()).distinct().toList();

        String token = jwtProvider.generateToken(user.getId(), user.getGmail(), role, permissions);
        return new UserReponseDTO(
                user.getName(),
                user.getRole(),
                token
        );
    }
    @Override
    @PermissionCheck(resource = ResourceType.USER, action = Action.CREATE)
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

}
