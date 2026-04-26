package com.internship.xchat.user_service.controller;

import com.internship.xchat.user_service.dto.UserDTO;
import com.internship.xchat.user_service.entity.User;
import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import com.internship.xchat.user_service.service.UserService;

import com.internship.xchat.user_service.service.impl.FileServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.validation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import  org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "${app.api.user}")
public class UserController {

  private UserService userService;
  private FileServiceImpl fileService;

    public UserController(UserService userService, FileServiceImpl fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping
    public Page<User> getAllusers(@RequestParam(value = "page") Pageable page)
    {
        return this.userService.getAllUsers(page);
    }

    @GetMapping(path = "${app.api.by-id}")
    public User getUserbyId(@PathVariable("id") Long id)
    {
        return this.userService.getUser(id);
    }

    @DeleteMapping
    public void deleteAllUsers()
    {
        this.userService.deleteAllUsers();
    }

    @DeleteMapping(path = "${app.api.by-id}")
    public void deleteUserbyId(@PathVariable("id") Long id)
    {
        this.userService.delete(id);
    }

    @RequestMapping(path = "${app.api.by-id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public User updateUser(@Valid @RequestBody UserDTO dto, @Valid @PathVariable("id") Long id) {
        return userService.update(dto, id);
    }

    @GetMapping(path = "/search")
    public Page<User> searchUsersByName(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "email",required = false) String email,
                                        @RequestParam(value = "number",required = false) String number,
                                        Pageable page)
    {
        return this.userService.search(name, email, number, page);
    }

    @GetMapping(path = "/info")
    public User getLoggedInUserInfo()
    {
        Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
        Claims userClaims = (Claims)userAuthentication.getPrincipal();
        Long userId = userClaims.get("id", Long.class);
        return this.userService.getUser(userId);
    }

    @GetMapping(path = "{id}/image")
    public String getUserImage(@PathVariable("id") Long id)
    {
        String image = this.fileService.getImageUrl(id);
        if (image == null) throw new ResourceNotFoundException("Image for user " + id + " is not found");
        return image;
    }

    @PostMapping(path = "{id}/image")
    public String uploadImage(@PathVariable("id") Long uid, @RequestBody String image) {
        return this.fileService.uploadImage(uid, image);
    }

}

