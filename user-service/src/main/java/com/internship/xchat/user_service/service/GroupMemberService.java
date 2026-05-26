package com.internship.xchat.user_service.service;

import com.internship.xchat.user_service.dto.GroupMemberDTO;
import com.internship.xchat.user_service.entity.*;


public interface GroupMemberService {
    public GroupMember addMember(GroupMemberDTO groupMemberDTO);
    public void removeMember(Long userId, Long groupId);
    public boolean isAdmin(Long groupId, Long userId);
}
