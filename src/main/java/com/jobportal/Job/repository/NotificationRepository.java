package com.jobportal.Job.repository;

import com.jobportal.Job.dto.NotificationStatus;
import com.jobportal.Job.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
    public List<Notification> findByUserIdAndStatus(Long userId, NotificationStatus status);
}
