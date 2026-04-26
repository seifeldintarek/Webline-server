package com.internship.xchat.user_service.controller;

import com.internship.xchat.user_service.dto.FriendshipDTO;
import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import com.internship.xchat.user_service.service.FriendshipService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService)
    {
        this.friendshipService = friendshipService;
    }

    @GetMapping(path = "{userId}/friends")
    public Page<User> getFriends(@PathVariable("userId") Long id, Pageable page) {
        return this.friendshipService.getAllFriends(id, page);
    }

    @GetMapping(path = "{userId}/requests/sent")
    public Page<User> getMyRequests(@PathVariable("userId") Long id, Pageable page)
    {
        return this.friendshipService.getMyRequests(id,page);
    }

    @GetMapping(path = "{userId}/requests/received")
    public Page<User> getFriendRequests(@PathVariable("userId") Long id, Pageable page)
    {
        return this.friendshipService.getFriendRequests(id, page);
    }

    @GetMapping(path = "{userId}/requests/all")
    public Page<User> getAllRequests(@PathVariable("userId") Long id, Pageable page)
    {
        return this.friendshipService.getAllRequests(id, page);
    }


    @PostMapping("/addFriend")
    public User addFriend(@RequestBody FriendshipDTO dto) {
      return this.friendshipService.addFriend(dto);
    }

    @PostMapping("/acceptFriend")
    public Friendship acceptFriend(@RequestBody FriendshipDTO dto) {
        Friendship friendship = this.friendshipService.acceptFriend(dto);
        if (friendship == null) {
            throw new ResourceNotFoundException("Friendship");
        }
        return friendship;
    }

    @DeleteMapping("/deleteFriendship")
    public void deleteFriendship(@RequestBody FriendshipDTO dto) {
        this.friendshipService.deleteFriendship(dto);
    }

}
