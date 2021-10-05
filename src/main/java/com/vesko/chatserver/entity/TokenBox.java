package com.vesko.chatserver.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "token_box")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class TokenBox extends BaseEntity {
    private String access;
    private String refresh;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "tokenBox")
    private User user;
}
