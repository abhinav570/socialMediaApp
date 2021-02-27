package com.abhinav.socialmedia.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NonNull
    private String userName;

    @NonNull
    private String userId;

}
