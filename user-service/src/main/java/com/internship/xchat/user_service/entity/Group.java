package com.internship.xchat.user_service.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @SequenceGenerator(sequenceName = "group_sequence", name = "group_sequence", allocationSize = 1)
    @GeneratedValue(generator = "group_sequence", strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(updatable = false,nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String image;
    @ManyToOne
    @JoinColumn(name = "created_by",nullable = false)
    private User createdBy;
}
