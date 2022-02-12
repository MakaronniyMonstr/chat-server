package com.vesko.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.TokenBox;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TokenBoxDto extends BaseDto<TokenBox> {
    @JsonView(OutputViews.Message.class)
    private String access;

    @JsonView(OutputViews.Message.class)
    @NotNull(groups = {InputViews.New.class})
    private String refresh;

    @JsonView(OutputViews.Message.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto user;

    public TokenBoxDto(TokenBox entity) {
        super(entity);
    }
}
