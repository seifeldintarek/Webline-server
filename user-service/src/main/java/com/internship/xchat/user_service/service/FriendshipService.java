package com.internship.xchat.user_service.service;

import com.internship.xchat.user_service.dto.FriendshipDTO;
import com.internship.xchat.user_service.entity.Friendship;
import com.internship.xchat.user_service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FriendshipService {
    public User addFriend(FriendshipDTO dto);

    public Friendship acceptFriend(FriendshipDTO dto);

    public void deleteFriendship(FriendshipDTO dto);

    public Page<User> getAllFriends(Long id , Pageable page);

    public Page<User> getMyRequests(Long id, Pageable page);

    public  Page<User> getFriendRequests(Long id, Pageable page);

    public Page<User> getAllRequests(Long id , Pageable page);
}
