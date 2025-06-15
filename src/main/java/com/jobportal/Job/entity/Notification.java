package com.jobportal.Job.entity;

import com.jobportal.Job.dto.NotificationDTO;

import com.jobportal.Job.dto.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timestamp;

    public NotificationDTO toDTO() {
        return new NotificationDTO(this.id, this.userId, this.message, this.action, this.route, this.status, this.timestamp);
    }
}
