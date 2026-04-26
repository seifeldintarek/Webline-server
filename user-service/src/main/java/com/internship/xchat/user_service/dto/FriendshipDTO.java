package com.internship.xchat.user_service.dto;

import com.internship.xchat.user_service.enums.FriendshipStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FriendshipDTO {

    private Long id;
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FriendshipStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

}
