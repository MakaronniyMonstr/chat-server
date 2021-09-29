package com.vesko.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.validator.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends BaseDTO<User> {
    @JsonView({OutputViews.Short.class})
    @NotNull(groups = {InputViews.General.class})
    private String username;

    @JsonView({OutputViews.Admin.class})
    @Password(groups = {InputViews.New.class})
    @Null(groups = {InputViews.Exists.class})
    private String password;

    @JsonView({OutputViews.Detailed.class})
    private boolean accountNonExpired;

    @JsonView({OutputViews.Detailed.class})
    private boolean accountNonLocked;

    @JsonView({OutputViews.Detailed.class})
    private boolean credentialsNonExpired;

    @JsonView({OutputViews.Detailed.class})
    private boolean enabled;

    public UserDTO(User user) {
        super(user);
    }
}
