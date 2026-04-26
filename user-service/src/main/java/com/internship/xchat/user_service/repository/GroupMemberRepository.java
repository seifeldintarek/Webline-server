package com.internship.xchat.user_service.repository;

import com.internship.xchat.user_service.entity.*;
import com.internship.xchat.user_service.enums.GroupMemberRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

   public GroupMember findByGroup_IdAndMember_Id(Long groupId, Long userId);
   public Page<GroupMember> findByGroup_IdAndRole(Long groupId, GroupMemberRole role, Pageable pageable);
   public void deleteByGroup_IdAndMember_Id(Long groupId, Long userId);
   public Page<GroupMember> findAllByGroup_Id(Long groupId, Pageable pageable);
   public void deleteByGroup_Id(Long groupId);
}
