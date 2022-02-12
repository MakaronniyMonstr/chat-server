package com.vesko.chatserver.dto.mapper;

import com.vesko.chatserver.dto.MessageDto;
import com.vesko.chatserver.entity.Message;
import com.vesko.chatserver.service.IKeyBoxService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = IKeyBoxService.class, injectionStrategy = InjectionStrategy.FIELD)
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "text", target = "text", qualifiedByName = "decryptText")
    Message messageDtoToMessage(MessageDto messageDto);

    @Mapping(source = "text", target = "text", qualifiedByName = "encryptText")
    MessageDto messageToMessageDto(Message message);

    @Mapping(source = "text", target = "text", qualifiedByName = "decryptText")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMessageFromMessageDto(MessageDto messageDto, @MappingTarget Message message);
}
