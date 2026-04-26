package com.internship.xchat.user_service.controller;

import com.internship.xchat.user_service.dto.*;
import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.user_service.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/group")
public class GroupController {

    private final GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/{groupId}")
    public Group getGroup(@PathVariable("groupId") Long groupId) {
       return this.groupService.getGroup(groupId);
    }

    @GetMapping(path = "/{groupId}/members")
    public Page<GroupMember> getAllMembers(@PathVariable("groupId") Long groupId, Pageable page) {
        return this.groupService.getAllMembers(groupId, page);
    }

    @GetMapping(path = "/{groupId}/admin")
    public Page<GroupMember> getAdmin(@PathVariable("groupId") Long groupId, Pageable page) {
        Page<GroupMember> admins = this.groupService.getAdmin(groupId,page);
        return admins == null ? Page.empty() : admins;
    }

    @GetMapping(path = "/{groupId}/isMember/{userId}")
public boolean isMember(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        return this.groupService.isMember(groupId, userId);
    }

    @PostMapping
    public Group createGroup(@RequestBody GroupDTO groupDTO) {
        if (groupDTO == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Data can not be null");
        return this.groupService.createGroup(groupDTO);
    }

    @RequestMapping(path = "/{groupId}/setAdmin/{userId}", method = {RequestMethod.PUT,RequestMethod.PATCH})
    public GroupMember setAdmin(@PathVariable("groupId") Long groupId, @PathVariable("userId") Long userId) {
        return this.groupService.setAdmin(groupId, userId);
    }

    @RequestMapping(path = "/{groupId}", method = {RequestMethod.PUT,RequestMethod.PATCH})
    public Group updateGroup(@RequestBody GroupDTO groupDTO, @PathVariable("groupId") Long groupId) {
        groupDTO.setId(groupId);
        return this.groupService.update(groupDTO);
    }


    @DeleteMapping(path = "/{groupId}")
    public void deleteGroup(@PathVariable("groupId") Long groupId) {
        this.groupService.deleteGroup(groupId);
    }





}
