package com.internship.xchat.user_service.service.impl;

import com.internship.xchat.user_service.dto.FriendshipDTO;
import com.internship.xchat.user_service.entity.Friendship;
import com.internship.xchat.user_service.entity.User;
import com.internship.xchat.user_service.enums.FriendshipStatus;
import com.internship.xchat.common_lib.exception.ResourceNotFoundException;
import com.internship.xchat.user_service.mapper.FriendshipMapper;
import com.internship.xchat.user_service.repository.*;
import com.internship.xchat.user_service.service.FriendshipService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    public FriendshipServiceImpl(FriendsRepository friendsRepository, UserRepository userRepository)
    {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }



    @Override
    public User addFriend(FriendshipDTO dto) {

        Friendship friendship = FriendshipMapper.toEntity(dto);
        this.friendsRepository.save(friendship);
        User friend = this.userRepository.findById(dto.getReceiverId()).get();
        return friend;
    }

    @Override
    public Friendship acceptFriend(FriendshipDTO dto) {

        Optional<Friendship> friendship = this.friendsRepository.findBySenderIdAndReceiverId(dto.getSenderId(), dto.getReceiverId());

       if (friendship.isPresent())
       {
           friendship.get().setStatus(FriendshipStatus.FRIENDS);
           this.friendsRepository.save(friendship.get());
           return friendship.get();
       }

       throw new ResourceNotFoundException("Friendship");
    }

    @Override
    public void deleteFriendship(FriendshipDTO dto)
    {

        try {
            this.friendsRepository.deleteFriendship(dto.getSenderId(), dto.getReceiverId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Page<User> getAllFriends(Long userId, Pageable page) {
        Page<User> friends = this.friendsRepository.findUserFriends(userId,page);

        return friends == null ? Page.empty() : friends;

    }

    @Override
    public Page<User> getMyRequests(Long id, Pageable page) {
        Page<User> friendRequests = this.friendsRepository.findAllBySenderId(id, page);

        return friendRequests == null ?
             Page.empty() : friendRequests;
    }

    @Override
    public Page<User> getFriendRequests(Long id, Pageable page) {

        Page<User> friendRequests = this.friendsRepository.findAllByReceiverId(id,page);

        return friendRequests == null ?
                Page.empty() : friendRequests;

    }

    @Override
    public Page<User> getAllRequests(Long id, Pageable page) {
        Page<User> requests = this.friendsRepository.findAllRequests(id,page);
        return requests == null ? Page.empty() : requests;
    }
}


