package com.internship.xchat.user_service.controller;

import com.internship.xchat.user_service.dto.*;
import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.user_service.service.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupMember")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;

    public GroupMemberController(GroupMemberService groupMemberService) {
        this.groupMemberService = groupMemberService;
    }

    @PostMapping
    public GroupMember addMember(@RequestBody GroupMemberDTO groupMemberDTO) {
        return this.groupMemberService.addMember(groupMemberDTO);
    }

    @DeleteMapping
    public void removeMember(@RequestParam("userId") Long userId, @RequestParam("groupId") Long groupId) {
        this.groupMemberService.removeMember(userId, groupId);
    }
}
