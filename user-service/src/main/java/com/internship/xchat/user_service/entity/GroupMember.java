package com.internship.xchat.user_service.entity;

import com.internship.xchat.user_service.enums.GroupMemberRole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"group_id", "user_id"}))
public class GroupMember {
    @SequenceGenerator(sequenceName = "groupMember_sequence", name = "groupMember_sequence", allocationSize = 1)
    @GeneratedValue(generator = "groupMember_sequence", strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id",nullable = false)
    private Group group;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User member;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime joinedAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupMemberRole role;

}
