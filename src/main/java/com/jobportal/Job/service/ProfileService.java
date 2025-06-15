package com.jobportal.Job.service;

import com.jobportal.Job.dto.ProfileDTO;
import com.jobportal.Job.exceptions.JobPortalException;

import java.util.List;

public interface ProfileService {
    public Long createProfile(String email, String name) throws JobPortalException;

    public ProfileDTO getProfile(Long id) throws JobPortalException;

    public ProfileDTO updateProfile(ProfileDTO profileDto) throws JobPortalException;

    public List<ProfileDTO> getAllProfile();

}
