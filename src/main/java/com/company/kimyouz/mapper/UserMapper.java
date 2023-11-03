package com.company.kimyouz.mapper;

import com.company.kimyo.uz.dto.UserDto;
import com.company.kimyo.uz.model.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class UserMapper {

    @Lazy
    @Autowired
    protected CardMapper cardMapper;

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "cards", ignore = true)
    public abstract User toEntity(UserDto dto);

    @Mapping(target = "cards", ignore = true)
    public abstract UserDto toDto(User user);

    @Mapping(target = "cards", expression = "java(user.getCards().stream().map(this.cardMapper::toDto).collect(Collectors.toSet()))")
    public abstract UserDto toDtoWithCard(User user);

    public void simple() {
        UserDto userDto = new UserDto();
        User user = new User();
    }


    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, resultType = User.class)
    public abstract User updateUser(UserDto dto, @MappingTarget User user);


}
