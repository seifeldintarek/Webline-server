package com.internship.xchat.user_service.mapper;

import com.internship.xchat.user_service.dto.FriendshipDTO;
import com.internship.xchat.user_service.entity.Friendship;
import com.internship.xchat.user_service.enums.FriendshipStatus;

public class FriendshipMapper {

    public static Friendship toEntity(FriendshipDTO dto)
    {
        Friendship friendship = new Friendship();

        friendship.setId(dto.getId());
        friendship.setSenderId(dto.getSenderId());
        friendship.setReceiverId(dto.getReceiverId());

        if (dto.getStatus() != null) {
            friendship.setStatus(dto.getStatus());
        }
        else {
            friendship.setStatus(FriendshipStatus.PENDING);
        }

        return friendship;
    }

    public static FriendshipDTO toDTO(Friendship friendship)
    {
        FriendshipDTO dto = new FriendshipDTO();

        dto.setId(friendship.getId());
        dto.setSenderId(friendship.getSenderId());
        dto.setReceiverId(friendship.getReceiverId());
        dto.setStatus(friendship.getStatus());
        dto.setCreatedAt(friendship.getCreatedAt());
        dto.setUpdatedAt(friendship.getUpdatedAt());

        return dto;
    }
}
