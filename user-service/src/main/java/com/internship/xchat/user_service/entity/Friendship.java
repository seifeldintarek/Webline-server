package com.internship.xchat.user_service.entity;

import com.internship.xchat.user_service.enums.FriendshipStatus;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.*;

@Table(name = "friendship")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Friendship {
    @SequenceGenerator(initialValue = 1, sequenceName = "friendship_sequence", name = "friendship_sequence", allocationSize = 1)
    @GeneratedValue(generator = "friendship_sequence", strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long senderId;
    @Column(nullable = false)
    private Long receiverId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendshipStatus status;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;



    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = FriendshipStatus.PENDING;
        }
    }


}
