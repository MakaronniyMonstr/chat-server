package com.vesko.chatserver.dto.mapper;

import com.vesko.chatserver.dto.UserDto;
import com.vesko.chatserver.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDTO(User user);
    User toEntity(UserDto userDTO);
}
