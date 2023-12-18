package com.fsc.external.portal.controllers;

import com.fsc.external.portal.dtos.PageResponse;
import com.fsc.external.portal.dtos.notification.NotificationResponse;
import com.fsc.external.portal.services.notification.NotificationAdditionalService;
import io.jmix.notifications.NotificationReadStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/notifications")
public class NotificationController {

    private final NotificationAdditionalService notificationAdditionalService;

    public NotificationController(NotificationAdditionalService notificationAdditionalService) {
        this.notificationAdditionalService = notificationAdditionalService;
    }

    @Operation(summary = "Updates the status of all notifications to READ")
    @PutMapping("/update-all-statuses")
    public void updateAllStatusesToRead() {

        notificationAdditionalService.markAllNotificationStatusesAsRead();
    }

    @Operation(summary = "Updates the status of the notification")
    @PatchMapping("/update-status")
    public void updateReadStatus(
            @Parameter(required = true, in = ParameterIn.QUERY,
                       description = "The id of the notification for which we want to update the status")
            @RequestParam("id") final String id,
            @Parameter(required = true, in = ParameterIn.QUERY,
                       description = "The status we want to set to the notification")
            @RequestParam("readStatus") final NotificationReadStatus readStatus) {
        notificationAdditionalService.updateNotificationStatus(id, readStatus);
    }

    @Operation(summary = "Returns the notifications for the logged user in a page format")
    @PageableAsQueryParam
    @GetMapping("/notifications")
    public ResponseEntity<PageResponse<NotificationResponse>> getNotificationsByAuthenticatedUser(
            @Parameter(hidden = true)
                    Pageable pageable) {
        return ResponseEntity.ok(notificationAdditionalService.getNotificationsByAuthenticatedUser(pageable));
    }
}