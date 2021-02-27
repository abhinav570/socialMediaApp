package com.abhinav.socialmedia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowID implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "FOLLOWER_ID")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "FOLLOWEE_ID")
    private User followee;

}
