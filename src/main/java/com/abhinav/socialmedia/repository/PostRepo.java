package com.abhinav.socialmedia.repository;

import com.abhinav.socialmedia.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {

    List<Post> findAll();
    @Query(value = "SELECT POST.POST_CONTENT FROM POST POST WHERE POST.POST_OWNER IN ?1 ORDER BY POST.POST_TIME DESC LIMIT ?2", nativeQuery = true)
    List<String> getNewsFeeds(List<String> userIdList, Integer postLimit);

}

