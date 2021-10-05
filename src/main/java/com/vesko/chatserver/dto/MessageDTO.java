package com.vesko.chatserver.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO extends BaseDTO<Message> {
    @JsonView({OutputViews.Detailed.class})
    private String text;

    public MessageDTO(Message entity) {
        super(entity);
    }
}
