package com.vesko.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class MessageDto implements Serializable {
    @JsonView(OutputViews.Message.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonView(OutputViews.Message.class)
    @NotNull(groups = {InputViews.General.class})
    private String text;

    @JsonView(OutputViews.Message.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDto user;
}
