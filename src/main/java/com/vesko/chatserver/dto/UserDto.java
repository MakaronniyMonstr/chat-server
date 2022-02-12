package com.vesko.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.validator.Password;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
public class UserDto extends BaseDto<User> {
    @JsonView({OutputViews.Short.class, OutputViews.Message.class})
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonView({OutputViews.Short.class, OutputViews.Message.class})
    @NotNull(groups = {InputViews.General.class})
    private String username;

    @JsonView({OutputViews.Admin.class})
    @Password(groups = {InputViews.New.class})
    @Null(groups = {InputViews.Exists.class})
    private String password;

    @JsonView({OutputViews.Detailed.class})
    private boolean accountNonExpired = true;

    @JsonView({OutputViews.Detailed.class})
    private boolean accountNonLocked = true;

    @JsonView({OutputViews.Detailed.class})
    private boolean credentialsNonExpired = true;

    @JsonView({OutputViews.Detailed.class})
    private boolean enabled = true;

    public UserDto(User user) {
        super(user);
    }
}
