package com.abhinav.socialmedia.repository;

import com.abhinav.socialmedia.entity.Follow;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepo extends CrudRepository<Follow, Long> {

    List<Follow> findAll();

    @Query(value = "DELETE FROM FOLLOW F WHERE F.FOLLOWER_ID = ?1 AND F.FOLLOWEE_ID=?2", nativeQuery = true)
    @Modifying
    int unfollow(String followerID, String followeeID);

    @Query(value = "INSERT INTO FOLLOW (FOLLOWER_ID, FOLLOWEE_ID) VALUES (?1, ?2)", nativeQuery = true)
    @Modifying
    int follow(String followerID, String followeeID);

    @Query(value = "SELECT FOLLOW.FOLLOWEE_ID FROM FOLLOW FOLLOW WHERE FOLLOW.FOLLOWER_ID = ?1", nativeQuery = true)
    List<String> getFolloweesByFollower(String followerId);
}

