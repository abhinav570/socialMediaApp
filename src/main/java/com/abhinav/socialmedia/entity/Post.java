package com.abhinav.socialmedia.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "POST")
public class Post {
    @Id
    @Column(name = "POST_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;

    @Column(name = "POST_CONTENT", nullable = false)
    private String postContent;

    @ManyToOne
    @JoinColumn(name = "POST_OWNER", nullable = false)
    private User postOwner;

    @Column(name = "POST_TIME", nullable = false)
    private Timestamp postTime;

}

