package com.internship.xchat.user_service.mapper;

import com.internship.xchat.user_service.dto.GroupMemberDTO;
import com.internship.xchat.user_service.entity.GroupMember;
import com.internship.xchat.user_service.enums.GroupMemberRole;

public class GroupMemberMapper {
    public static GroupMember toEntity(GroupMemberDTO dto)
    {
        GroupMember groupMember = new GroupMember();
        groupMember.setId(dto.getId());
        groupMember.setGroup(dto.getGroup());
        groupMember.setMember(dto.getMember());
        groupMember.setRole(GroupMemberRole.MEMBER);
        return groupMember;
    }

    public static GroupMemberDTO toDTO(GroupMember groupMember){
        GroupMemberDTO dto = new GroupMemberDTO();
        dto.setId(groupMember.getId());
        dto.setGroup(groupMember.getGroup());
        dto.setMember(groupMember.getMember());
        dto.setRole(groupMember.getRole());
        dto.setJoinedAt(groupMember.getJoinedAt());
        return dto;
    }
}
