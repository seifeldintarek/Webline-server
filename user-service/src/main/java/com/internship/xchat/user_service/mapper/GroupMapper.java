package com.internship.xchat.user_service.mapper;

import com.internship.xchat.user_service.dto.GroupDTO;
import com.internship.xchat.user_service.entity.Group;

public class GroupMapper {

    public static Group toEntity(GroupDTO dto)
    {
        Group group = new Group();
        group.setId(dto.getId());
        group.setCreatedBy(dto.getCreatedBy());
        if (dto.getName() != null) {
            group.setName(dto.getName().trim());
        }
        if (dto.getDescription() != null) {
            group.setDescription(dto.getDescription());
        }
        if (dto.getImage() != null) {
            group.setImage(dto.getImage());
        }
        return group;
    }

    public static GroupDTO toDTO(Group group)
    {
        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName().trim());
        dto.setDescription(group.getDescription().trim());
        dto.setImage(group.getImage());
        dto.setCreatedAt(group.getCreatedAt());
        dto.setUpdatedAt(group.getUpdatedAt());
        return dto;
    }
}
