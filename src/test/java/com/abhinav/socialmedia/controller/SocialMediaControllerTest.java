package com.abhinav.socialmedia.controller;

import com.abhinav.socialmedia.dto.FollowRequestDto;
import com.abhinav.socialmedia.dto.PostRequestDto;
import com.abhinav.socialmedia.dto.ResponseDto;
import com.abhinav.socialmedia.dto.UserDto;
import com.abhinav.socialmedia.entity.Post;
import com.abhinav.socialmedia.service.FollowService;
import com.abhinav.socialmedia.service.PostService;
import com.abhinav.socialmedia.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class SocialMediaControllerTest {

    @Autowired
    private TestRestTemplate RestTemplate;

    @MockBean
    private PostService postService;

    @MockBean
    private FollowService followService;

    @MockBean
    private UserService userService;

    @InjectMocks
    private SocialMediaController socialMediaController;

    @Test
    public void shouldAddPost(){
     when(postService.createPost(any(PostRequestDto.class))).thenReturn(ResponseDto.builder().message("Posted Successfully").build());
        RequestEntity requestEntity = RequestEntity.post("/post")
                .body(PostRequestDto.builder().postContent("post1").postOwner("user1").build());
        ResponseEntity responseEntity = RestTemplate.exchange(requestEntity, String.class);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void shouldAddFollower(){
        when(followService.follow(any(String.class), any(String.class))).thenReturn(ResponseDto.builder().message("follower added Successfully").build());
        RequestEntity requestEntity = RequestEntity.post("/follow")
                .body(FollowRequestDto.builder().followeeId("user1").followerId("user2").build());
        ResponseEntity responseEntity = RestTemplate.exchange(requestEntity, String.class);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void shouldUnFollow(){
        when(followService.unfollow(any(String.class), any(String.class))).thenReturn(ResponseDto.builder().message("unfollow user Successfully").build());
        RequestEntity requestEntity = RequestEntity.delete("/unfollow?followeeID=user1&followerID=user2").build();
        ResponseEntity responseEntity = RestTemplate.exchange(requestEntity, String.class);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

    @Test
    public void shouldGetNewsFeed(){
        when(postService.newsFeeds(any(String.class))).thenReturn(asList(Post.builder().postId(1L).build(),
                Post.builder().postId(2L).build()));
        RequestEntity requestEntity = RequestEntity.get("/newsFeeds?userId=user1").build();
        ResponseEntity<List> responseEntity = RestTemplate.exchange(requestEntity, List.class);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        Assert.assertFalse(responseEntity.getBody().isEmpty() );
    }

    @Test
    public void shouldAddUser(){
        when(userService.createUser(any(UserDto.class))).thenReturn(ResponseDto.builder().message("user added Successfully").build());
        RequestEntity requestEntity = RequestEntity.post("/user")
                .body(UserDto.builder().userId("user3").userName("JOHN").build());
        ResponseEntity responseEntity = RestTemplate.exchange(requestEntity, String.class);
        Assert.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }

}
