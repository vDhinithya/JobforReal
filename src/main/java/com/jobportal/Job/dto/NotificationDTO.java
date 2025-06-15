package com.jobportal.Job.dto;

import com.jobportal.Job.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timestamp;

    public Notification toEntity() {
        return new Notification(this.id, this.userId, this.message, this.action, this.route, this.status, this.timestamp);
    }

}
