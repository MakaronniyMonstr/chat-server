package com.vesko.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.TokenBox;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Getter
@Setter
public class TokenBoxDTO extends BaseDTO<TokenBox> {
    @JsonView(OutputViews.Detailed.class)
    private String access;

    @JsonView(OutputViews.Detailed.class)
    @NotNull(groups = {InputViews.New.class})
    private String refresh;

    public TokenBoxDTO(TokenBox entity) {
        super(entity);
    }
}
