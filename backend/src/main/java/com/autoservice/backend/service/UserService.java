package com.autoservice.backend.service;

import com.autoservice.backend.dto.LoginRequestDTO;
import com.autoservice.backend.dto.RegisterRequestDTO;
import com.autoservice.backend.enums.Role;
import com.autoservice.backend.model.User;
import com.autoservice.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterRequestDTO mapToDTO(User register) {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setEmail(register.getEmail());
        dto.setPassword(register.getPassword());
        dto.setFirstName(register.getFirstName());
        dto.setLastName(register.getLastName());
        dto.setPhoneNumber(register.getPhoneNumber());

        return dto;
    }

    @Transactional
    public RegisterRequestDTO registerRequest(RegisterRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email address is already in use!");
        }

        User user = new User();
        user.setEmail(dto.getEmail());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.CLIENT);
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setPhoneNumber(dto.getPhoneNumber());

        User saved = userRepository.save(user);
        RegisterRequestDTO responseDto = mapToDTO(saved);
        responseDto.setPassword(null);

        return responseDto;
    }

    public LoginRequestDTO loginRequestDTO(LoginRequestDTO dto) {
        return null;
    }
}
