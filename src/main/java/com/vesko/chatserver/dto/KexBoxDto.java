package com.vesko.chatserver.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class KexBoxDto implements Serializable {
    private String publicKey;
}
