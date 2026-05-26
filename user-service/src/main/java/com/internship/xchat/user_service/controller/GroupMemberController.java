package com.internship.xchat.user_service.controller;

import com.internship.xchat.user_service.dto.*;
import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.user_service.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupMember")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;
    private final  GroupService groupService;

    public GroupMemberController(GroupMemberService groupMemberService, GroupService groupService) {
        this.groupMemberService = groupMemberService;
        this.groupService = groupService;
    }

    @PostMapping
    public GroupMember addMember(@RequestBody GroupMemberDTO groupMemberDTO) {
        return this.groupMemberService.addMember(groupMemberDTO);
    }

    @DeleteMapping("/{groupId}/{userId}")
    public void removeMember(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId) {
        this.groupMemberService.removeMember(userId, groupId);
    }

    @GetMapping(path = "{groupId}/{userId}/isAdmin")
    public boolean isAdmin(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId)
    {
        return this.groupMemberService.isAdmin(groupId, userId);
    }

    @GetMapping(path = "/{groupId}/isMember/{userId}")
    public boolean isMember(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        return this.groupService.isMember(groupId, userId);
    }

    @RequestMapping(path = "/{groupId}/setAdmin/{userId}", method = {RequestMethod.PUT,RequestMethod.PATCH})
    public GroupMember setAdmin(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        return this.groupService.setAdmin(groupId, userId);
    }
}
