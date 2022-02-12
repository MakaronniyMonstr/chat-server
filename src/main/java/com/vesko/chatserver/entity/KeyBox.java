package com.vesko.chatserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "key_box")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class KeyBox extends BaseEntity {
    private String sessionKey;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "keyBox")
    private User user;
}
