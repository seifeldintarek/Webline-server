package com.internship.xchat.user_service.service.impl;

import com.internship.xchat.user_service.dto.*;
import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.user_service.enums.GroupMemberRole;
import com.internship.xchat.common_lib.exception.*;
import com.internship.xchat.user_service.mapper.*;
import com.internship.xchat.user_service.repository.*;
import com.internship.xchat.user_service.service.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMemberRepository groupMemberRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Group createGroup(GroupDTO dto) {

        User creator = this.userRepository.findById(dto.getCreatedBy().getId()).get();
        Group group = GroupMapper.toEntity(dto);
        group.setCreatedBy(creator);

        GroupMember groupMember = new GroupMember();
        groupMember.setGroup(group);
        groupMember.setRole(GroupMemberRole.ADMIN);
        groupMember.setMember(creator);

        this.groupRepository.save(group);
        this.groupMemberRepository.save(groupMember);

        return group;
    }

    @Transactional
    @Override
    public void deleteGroup(Long groupId) {
        try {
            this.groupMemberRepository.deleteByGroup_Id(groupId);
            this.groupRepository.deleteById(groupId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Group getGroup(Long groupId) {
        return this.groupRepository.findById(groupId).get();
    }

    @Override
    public Page<GroupMember> getAllMembers(Long groupId, Pageable page) {
        return this.groupMemberRepository.findAllByGroup_Id(groupId, page);
    }

    @Override
    public boolean isMember(Long groupId, Long userId) {

        GroupMember groupMember = this.groupMemberRepository.findByGroup_IdAndMember_Id(groupId,userId);

     return groupMember != null;

    }

    @Override
    public Group update(GroupDTO dto) {

        Group group = this.groupRepository.findById(dto.getId()).get();

        if (dto.getName() != null) {
            group.setName(dto.getName().trim());
        }
        if (dto.getDescription() != null) {
            group.setDescription(dto.getDescription());
        }
        if (dto.getImage() != null) {
            group.setImage(dto.getImage());
        }

        this.groupRepository.save(group);
        return group;
    }

    @Override
    public Page<GroupMember> getAdmin(Long groupId, Pageable page) {
       return this.groupMemberRepository.findByGroup_IdAndRole(groupId, GroupMemberRole.ADMIN, page);
    }

    @Override
    public GroupMember setAdmin(Long groupId, Long userId) {


        GroupMember groupMember = this.groupMemberRepository.findByGroup_IdAndMember_Id(groupId,userId);

        if (groupMember.getRole().equals(GroupMemberRole.ADMIN))
            throw new BadRequestException("This member is already an admin");

        groupMember.setRole(GroupMemberRole.ADMIN);
        this.groupMemberRepository.save(groupMember);

        return groupMember;

    }
}
