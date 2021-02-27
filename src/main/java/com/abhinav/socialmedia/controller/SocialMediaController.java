package com.abhinav.socialmedia.controller;

import com.abhinav.socialmedia.dto.FollowRequestDto;
import com.abhinav.socialmedia.dto.PostRequestDto;
import com.abhinav.socialmedia.dto.ResponseDto;
import com.abhinav.socialmedia.dto.UserDto;
import com.abhinav.socialmedia.service.FollowService;
import com.abhinav.socialmedia.service.PostService;
import com.abhinav.socialmedia.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(value="Demo Purpose", description="Social Media Demo")
public class SocialMediaController {

    @Autowired
    private PostService postService;

    @Autowired
    private FollowService followService;

    @Autowired
    private UserService userService;

    @PostMapping(value="/post", produces = "application/json")
    @ApiOperation(value = "Add new Post for User", notes = "", response = ResponseDto.class, produces = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 404, message = "resource you were trying to reach is not found"),@ApiResponse(code = 400, message = "bad request")})
    public ResponseEntity<ResponseDto> addPost(@RequestBody PostRequestDto request){
        log.info("Adding new Post");
        return ResponseEntity.ok().body(postService.createPost(request));
    }

    @PostMapping(value="/user", produces = "application/json")
    @ApiOperation(value = "Add new User", notes = "", response = ResponseDto.class, produces = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 404, message = "resource you were trying to reach is not found")
            ,@ApiResponse(code = 400, message = "bad request")})
    public ResponseEntity<ResponseDto> addUser(@RequestBody UserDto request){
        log.info("Adding new User");
        return ResponseEntity.ok().body(userService.createUser(request));
    }

    /*@DeleteMapping("/post/{id}")
    @ApiOperation(value = "API to delete post by id", notes = "", response = ResponseDto.class, produces = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 404, message = "resource you were trying to reach is not found"),@ApiResponse(code = 400, message = "bad request")})
    public ResponseEntity<ResponseDto> deletePostById(@PathVariable Long id){
        log.debug("testing delete post logger:: ");
        postService.deleteById(id);
        return ResponseEntity.ok().body(ResponseDto.builder().message("Post removed Successfully").build());
    }*/

    @PostMapping(value="/follow", produces = "application/json")
    @ApiOperation(value = "API provides provision to follow other user", notes = "", response = ResponseDto.class, produces = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 404, message = "resource you were trying to reach is not found"),@ApiResponse(code = 400, message = "bad request")})
    public ResponseEntity<ResponseDto> follow(@RequestBody FollowRequestDto request){
        log.info("New request for follow");
        ResponseDto responseDto = followService.follow(request.getFollowerId(),  request.getFolloweeId());
        return ResponseEntity.ok().body(responseDto);
    }

    @DeleteMapping("/unfollow")
    @ApiOperation(value = "API provides provision to un-follow existing followed user", notes = "", response = ResponseDto.class, produces = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 404, message = "resource you were trying to reach is not found"),@ApiResponse(code = 400, message = "bad request")})
    public ResponseEntity<ResponseDto>  unFollow(@RequestParam String followerID, @RequestParam String followeeID){
        log.info("New request for un-follow");
        ResponseDto responseDto = followService.unfollow(followerID, followeeID);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/newsFeeds")
    @ApiOperation(value = "Get new feed for the user", notes = "Latest 20 posts will displayed including user's", response = String.class, responseContainer = "List", produces = "application/json")
    @ApiResponses({@ApiResponse(code = 200, message = "success"), @ApiResponse(code = 404, message = "resource you were trying to reach is not found"),@ApiResponse(code = 400, message = "bad request")})
    public ResponseEntity<List<String>> newsFeeds(@RequestParam String userId){
        log.info("new Feeds for user "+userId);
        return ResponseEntity.ok().body(postService.newsFeeds(userId));
    }
}