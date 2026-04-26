package com.internship.xchat.user_service.repository;

import com.internship.xchat.user_service.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendsRepository extends JpaRepository<Friendship, Long> {



    //check if request/friendship exists
    Optional<Friendship> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    @Query(nativeQuery = true,
    value = "SELECT u.* FROM users u " +
            "JOIN friendship f " +
            "ON  u.id = f.receiver_id " +
            "WHERE ( f.sender_id = :senderId AND f.status = 'PENDING' )" ,
    countQuery = "SELECT COUNT(*) FROM users u " +
            "JOIN friendship f " +
            "ON  u.id = f.receiver_id " +
            "WHERE ( f.sender_id = :senderId AND f.status = 'PENDING' )" )
    Page<User> findAllBySenderId(@Param("senderId") Long senderId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM users u " +
                    "JOIN friendship f " +
                    "ON  u.id = f.sender_id " +
                    "WHERE ( f.receiver_id = :receiverId AND f.status = 'PENDING' )" ,
            countQuery = "SELECT COUNT(*) FROM users u " +
                    "JOIN friendship f ON u.id = f.sender_id " +
                    "WHERE f.receiver_id = :receiverId AND f.status = 'PENDING'")
    Page<User> findAllByReceiverId(@Param("receiverId") Long receiverId, Pageable pageable);

    @Query(nativeQuery = true,
    value = "SELECT u.* FROM users u " +
            "JOIN friendship f " +
            "ON ( " +
            "( f.sender_id = :userId AND u.id = f.receiver_id) " +
            "OR " +
            "( f.receiver_id = :userId AND u.id = f.sender_id)" +
            ")" +
            "WHERE f.status = 'FRIENDS'")
    Page<User> findUserFriends(@Param("userId") Long userId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM users u " +
                    "JOIN friendship f " +
                    "ON ( " +
                    "( f.sender_id = :userId AND u.id = f.receiver_id) " +
                    "OR " +
                    "( f.receiver_id = :userId AND u.id = f.sender_id)" +
                    ")" +
                    "WHERE f.status = 'PENDING'")
    Page<User> findAllRequests(@Param("userId") Long userId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "Delete from friendship f " +
                    "Where (f.sender_id = :senderId AND f.receiver_id = :recId) " +
                    "OR (f.sender_id = :recId AND f.receiver_id = :senderId) ")
    void deleteFriendship(@Param("senderId") Long senderId, @Param("recId") Long recId);


}
