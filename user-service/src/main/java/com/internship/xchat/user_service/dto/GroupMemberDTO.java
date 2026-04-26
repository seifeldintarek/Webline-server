package com.internship.xchat.user_service.dto;

import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.user_service.enums.GroupMemberRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberDTO {
    private Long id;
    private Group group;
    private User member;
    private GroupMemberRole role;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime joinedAt;
}
