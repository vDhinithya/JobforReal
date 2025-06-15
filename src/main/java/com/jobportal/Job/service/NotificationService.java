package com.jobportal.Job.service;

import com.jobportal.Job.dto.NotificationDTO;
import com.jobportal.Job.entity.Notification;
import com.jobportal.Job.exceptions.JobPortalException;

import java.util.List;

public interface NotificationService {
    public void sendNotification(NotificationDTO notificationDto) throws JobPortalException;

    public List<Notification> getUnreadNotifications(Long userId);

    public void readNotifications(Long id) throws JobPortalException;
}
