package com.abhinav.socialmedia.service;

import com.abhinav.socialmedia.repository.FollowRepo;
import com.abhinav.socialmedia.repository.PostRepo;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest extends TestCase {

    @Mock
    private FollowRepo followRepo;

    @Mock
    private PostRepo postRepo;

    @InjectMocks
    private PostService postService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFetchNewsFeed(){
        Mockito.when(followRepo.getFolloweesByFollower(anyString())).thenReturn(asList("user1","user2"));
        Mockito.when(postRepo.getNewsFeeds(anyList(),anyInt())).thenReturn(asList("post1","post2"));
        List<String> posts = postService.newsFeeds("user3");
        Assert.assertNotNull(posts);
        Assert.assertEquals(posts.size(), 2);
    }

}
