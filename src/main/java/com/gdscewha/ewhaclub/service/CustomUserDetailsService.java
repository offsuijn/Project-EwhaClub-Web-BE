package com.gdscewha.ewhaclub.service;

import com.gdscewha.ewhaclub.domain.User;
import com.gdscewha.ewhaclub.dto.UserDetailsDto;
import com.gdscewha.ewhaclub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<User> users = userRepository.findByEmail(userName)
                .stream()
                .collect(Collectors.toList());
        if(users.size() != 1) {
            return null;
        }
        else {
            return UserDetailsDto.create(users.get(0));
        }
    }
}
