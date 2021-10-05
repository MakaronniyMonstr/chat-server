package com.vesko.chatserver.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;

@Table(name = "room")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Room extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Message> messages;

    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private Set<RoomMember> roomMembers;
}
