package com.fsc.external.portal.mappers;

import com.fsc.external.portal.dtos.notification.NotificationResponse;
import io.jmix.notifications.entity.InAppNotification;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class}
)
public abstract class NotificationMapper implements BasePageResponseMapper<InAppNotification, NotificationResponse> {


    public abstract NotificationResponse toNotificationResponse(InAppNotification inAppNotification);

    public abstract List<NotificationResponse> toResponse(List<InAppNotification> notifications);


}
