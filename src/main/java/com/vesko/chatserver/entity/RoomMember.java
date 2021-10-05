package com.vesko.chatserver.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "room_member")
@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class RoomMember extends BaseEntity {
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @Enumerated(EnumType.STRING)
    @NonNull
    ERoomRole role;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    @NonNull
    private Room room;

}
