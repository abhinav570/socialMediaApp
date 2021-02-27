package com.abhinav.socialmedia.service;

import com.abhinav.socialmedia.dto.ResponseDto;
import com.abhinav.socialmedia.dto.UserDto;
import com.abhinav.socialmedia.entity.User;
import com.abhinav.socialmedia.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ResponseDto createUser(UserDto dto) {
        try {
            User user = User.builder().userId(dto.getUserId()).userName(dto.getUserName()).build();
            userRepo.save(user);
            return ResponseDto.builder().message("User Registered Successfully").build();
        }catch (Exception ex)
        {
            return ResponseDto.builder().message("Could not Create User due to "+ ex.getCause()).build();
        }
    }
}
