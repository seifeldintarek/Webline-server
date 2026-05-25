package com.internship.xchat.user_service.repository;

import com.internship.xchat.user_service.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Page<Group> findGroupByUserId(Long userId, Pageable pageable);
}
