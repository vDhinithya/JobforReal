package com.jobportal.Job.repository;

import com.jobportal.Job.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job,Long> {
    public List<Job> findByPostedBy(long postedBy);
}
