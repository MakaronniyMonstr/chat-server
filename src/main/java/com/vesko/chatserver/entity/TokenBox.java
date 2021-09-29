package com.vesko.chatserver.entity;

import lombok.*;

import javax.persistence.*;

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
            fetch = FetchType.LAZY,
            mappedBy = "tokenBox")
    private User user;
}
