package com.internship.xchat.user_service.service;

import com.internship.xchat.user_service.dto.GroupDTO;
import com.internship.xchat.user_service.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupService {
    public Group createGroup(GroupDTO groupDTO);
    public void deleteGroup(Long groupId);
    public Group getGroup(Long groupId);
    public Page<GroupMember> getAllMembers(Long groupId, Pageable pageable);
    public boolean isMember(Long groupId,Long userId);
    public Group update(GroupDTO groupDTO);
    public Page<GroupMember> getAdmin(Long groupid, Pageable pageable);
    public Page<Group> getAllGroups(Long userId,Pageable pageable);
    public GroupMember setAdmin(Long groupId,Long userId);
}
