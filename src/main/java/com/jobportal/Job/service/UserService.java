package com.jobportal.Job.service;

import com.jobportal.Job.dto.LoginDTO;
import com.jobportal.Job.dto.ResponseDTO;
import com.jobportal.Job.dto.UserDTO;
import com.jobportal.Job.exceptions.JobPortalException;


public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException;

    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException;

    public Boolean sendOtp(String email) throws Exception;

    public Boolean verifyOTP(String email, String otp) throws JobPortalException;

    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException;

}
