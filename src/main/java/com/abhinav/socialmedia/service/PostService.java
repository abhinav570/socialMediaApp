package com.abhinav.socialmedia.service;

import com.abhinav.socialmedia.dto.PostRequestDto;
import com.abhinav.socialmedia.dto.ResponseDto;
import com.abhinav.socialmedia.entity.Post;
import com.abhinav.socialmedia.entity.User;
import com.abhinav.socialmedia.repository.FollowRepo;
import com.abhinav.socialmedia.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    FollowRepo followRepo;

    @Value("${post.limit:20}")
    private int postLimit;

    public ResponseDto createPost(PostRequestDto dto) {
        try {
            Post post = Post.builder()
                    .postContent(dto.getPostContent())
                    .postOwner(User.builder().userId(dto.getPostOwner()).build())
                    .postTime(new Timestamp(System.currentTimeMillis()))
                    .build();
            postRepo.save(post);
            return ResponseDto.builder().message("Posted Successfully").build();
        }catch (Exception ex)
        {
            return ResponseDto.builder().message("Could not Post due to "+ ex.getCause()).build();
        }
    }

    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    public List<Post> newsFeeds(String followerId) {
        List<String> followeeList = followRepo.getFolloweesByFollower(followerId);
        followeeList.add(followerId);
        List<Post> posts = postRepo.getNewsFeeds(followeeList, postLimit);
        return posts;
    }
}
