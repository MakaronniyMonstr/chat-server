package com.vesko.chatserver.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vesko.chatserver.dto.MessageDto;
import com.vesko.chatserver.dto.mapper.MessageMapper;
import com.vesko.chatserver.dto.view.InputViews;
import com.vesko.chatserver.dto.view.JsonPage;
import com.vesko.chatserver.dto.view.OutputViews;
import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.entity.User;
import com.vesko.chatserver.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final IMessageService messageService;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageController(IMessageService messageService,
                             MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @PostMapping
    @JsonView({OutputViews.Message.class})
    public MessageDto create(@Validated(InputViews.New.class) @RequestBody MessageDto messageDto,
                             @AuthenticationPrincipal User user) {
        Message message = messageMapper.messageDtoToMessage(messageDto);
        message = messageService.create(user.getUsername(),
                message);

        return messageMapper.messageToMessageDto(message);
    }

    @GetMapping
    @JsonView({OutputViews.Message.class})
    public JsonPage<MessageDto> getAllByPage(Pageable pageable) {
        Page<MessageDto> page = messageService.findAllByPage(pageable)
                .map(messageMapper::messageToMessageDto);

        return new JsonPage<>(
                page.getContent(),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    @GetMapping("/{id}")
    public MessageDto getById(@PathVariable Long id) {
        return null;
    }
}
