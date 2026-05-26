package com.internship.xchat.user_service.service.impl;

import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import com.internship.xchat.user_service.dto.GroupMemberDTO;
import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.user_service.enums.GroupMemberRole;
import com.internship.xchat.user_service.mapper.GroupMemberMapper;
import com.internship.xchat.user_service.repository.*;
import com.internship.xchat.user_service.service.GroupMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;

    public GroupMemberServiceImpl(GroupMemberRepository groupMemberRepository) {
        this.groupMemberRepository = groupMemberRepository;

    }

    @Override
    public GroupMember addMember(GroupMemberDTO dto) {

        dto.setRole(GroupMemberRole.MEMBER);
        dto.setGroup(dto.getGroup());
        dto.setMember(dto.getMember());
        GroupMember groupMember = GroupMemberMapper.toEntity(dto);
        this.groupMemberRepository.save(groupMember);

        return groupMember;
    }

    @Override
    @Transactional
    public void removeMember(Long userId, Long groupId) {
        this.groupMemberRepository.deleteByGroup_IdAndMember_Id(groupId, userId);
    }

    @Override
    public boolean isAdmin(Long groupId, Long userId) {
        Optional<GroupMember> member = this.groupMemberRepository.findAdminMember(groupId);

        if(member.isPresent())
        {
            return userId.equals(member.get().getMember().getId());
        }
        else
            throw new ResourceNotFoundException("Admin or group");

    }
}
