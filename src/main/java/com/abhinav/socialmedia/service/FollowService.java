package com.abhinav.socialmedia.service;

import com.abhinav.socialmedia.dto.ResponseDto;
import com.abhinav.socialmedia.entity.Follow;
import com.abhinav.socialmedia.entity.FollowID;
import com.abhinav.socialmedia.entity.User;
import com.abhinav.socialmedia.repository.FollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FollowService {

    @Autowired
    FollowRepo followRepo;

    public ResponseDto follow(String followerId, String followeeId) {
        if(followeeId.equalsIgnoreCase(followeeId))
        {
            return ResponseDto.builder().message("Follower and Followee must not be same").build();
        }
        try {
            Follow followData = Follow.builder()
                    .followID(FollowID.builder()
                    .follower(User.builder().userId(followerId).build())
                    .followee(User.builder().userId(followeeId).build())
                    .build()).build();
            followRepo.save(followData);
            return ResponseDto.builder().message("Followed Successfully").build();
        }catch (Exception ex)
        {
            return ResponseDto.builder().message("Could not Follow due to "+ ex.getCause()).build();
        }
    }

    @Transactional
    public ResponseDto unfollow(String followerId, String followeeId) {
        if (followerId.equalsIgnoreCase(followeeId)) {
            return ResponseDto.builder().message("Follower and Followee must not be same").build();
        }
        try {
            int count = followRepo.unfollow(followerId, followeeId);
            if (count > 0) {
                return ResponseDto.builder().message("un-followed Successfully").build();
            }
            return ResponseDto.builder().message("No records to un-follow").build();
        } catch (Exception ex) {
            return ResponseDto.builder().message("Could not un-Follow due to " + ex.getCause()).build();

        }
    }

}
