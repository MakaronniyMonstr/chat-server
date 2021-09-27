package com.vesko.chatserver.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "token_box")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TokenBox extends BaseEntity {
    private String accessToken;
    private String refreshToken;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "tokenBox")
    private User user;
}
