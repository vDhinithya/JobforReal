package com.jobportal.Job.service;

import com.jobportal.Job.dto.NotificationDTO;
import com.jobportal.Job.dto.NotificationStatus;
import com.jobportal.Job.entity.Notification;
import com.jobportal.Job.exceptions.JobPortalException;
import com.jobportal.Job.repository.NotificationRepository;
import com.jobportal.Job.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements  NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationDTO notificationDto) throws JobPortalException {
        notificationDto.setId(Utilities.getNextSequence("notification"));
        notificationDto.setStatus(NotificationStatus.UNREAD);
        notificationDto.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notificationDto.toEntity());
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void readNotifications(Long id) throws JobPortalException {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new JobPortalException("No notification found"));
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);

    }
}
