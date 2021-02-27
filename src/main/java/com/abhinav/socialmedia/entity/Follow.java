package com.abhinav.socialmedia.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FOLLOW")
public class Follow {

    @EmbeddedId
    private FollowID followID;

    //@Column(name = "ACTIVE")
    //private boolean active;

}


