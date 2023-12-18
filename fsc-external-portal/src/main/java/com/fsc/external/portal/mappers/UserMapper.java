package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.User;
import com.fsc.external.portal.dtos.UserResponse;
import com.fsc.external.portal.mappers.person.PersonMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class},
        uses = {PersonMapper.class}

)
public abstract class UserMapper {

    public abstract UserResponse toUserResponse(User user);
}
