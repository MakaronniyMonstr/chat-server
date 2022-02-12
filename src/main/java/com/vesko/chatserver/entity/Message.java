package com.vesko.chatserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "message")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity {
    private String text;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
