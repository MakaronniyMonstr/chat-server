package com.vesko.chatserver.dto.mapper;

import com.vesko.chatserver.entity.TokenBox;
import com.vesko.chatserver.dto.TokenBoxDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TokenBoxMapper {
    TokenBoxMapper INSTANCE = Mappers.getMapper(TokenBoxMapper.class);

    TokenBox tokenBoxDtoToTokenBox(TokenBoxDto tokenBoxDto);

    TokenBoxDto tokenBoxToTokenBoxDto(TokenBox tokenBox);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTokenBoxFromTokenBoxDto(TokenBoxDto tokenBoxDto, @MappingTarget TokenBox tokenBox);
}
