package com.abhinav.socialmedia.dto;

import lombok.*;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class PostRequestDto {

    @NonNull
    private String postContent;

    @NonNull
    private String postOwner;

}
