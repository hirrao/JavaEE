package com.hirrao.javaee.converter;

import com.hirrao.javaee.entity.User;
import com.hirrao.javaee.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDto toUserDto(User user);
}
