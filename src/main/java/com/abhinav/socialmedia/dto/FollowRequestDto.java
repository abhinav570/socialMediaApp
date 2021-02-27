package com.abhinav.socialmedia.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequestDto {

    @NonNull
    String followerId;

    @NonNull
    String followeeId;
}
