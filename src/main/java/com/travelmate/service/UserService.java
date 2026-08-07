package com.travelmate.service;

import com.travelmate.dto.LoginRequest;
import com.travelmate.dto.UserResponse;
import com.travelmate.dto.UserSignupRequest;
import com.travelmate.entity.User;
import com.travelmate.exception.DuplicateLoginIdException;
import com.travelmate.exception.LoginFailedException;
import com.travelmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private UserResponse toResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getLoginId(),
                user.getName()
        );
    }

    private User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId).orElseThrow(LoginFailedException::new);
    }

    public UserResponse signup(UserSignupRequest request){
        if(userRepository.findByLoginId(request.getLoginId()).isPresent()){
            throw new DuplicateLoginIdException();
        }
        User user = new User(
                request.getLoginId(),
                request.getPassword(),
                request.getName()
                );
        User savedUser = userRepository.save(user);

        return toResponse(savedUser);
    }

    public UserResponse login(LoginRequest request) {
        User user = findByLoginId(request.getLoginId());
        if(!request.getPassword().equals(user.getPassword())){
            throw new LoginFailedException();
        }
        return toResponse(user);
    }
}
