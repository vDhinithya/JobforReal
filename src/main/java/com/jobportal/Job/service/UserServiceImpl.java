package com.jobportal.Job.service;

import com.jobportal.Job.dto.LoginDTO;
import com.jobportal.Job.dto.NotificationDTO;
import com.jobportal.Job.dto.ResponseDTO;
import com.jobportal.Job.dto.UserDTO;
import com.jobportal.Job.entity.OTP;
import com.jobportal.Job.entity.User;
import com.jobportal.Job.exceptions.JobPortalException;
import com.jobportal.Job.repository.OTPRepository;
import com.jobportal.Job.repository.UserRepository;
import com.jobportal.Job.utility.Data;
import com.jobportal.Job.utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private NotificationService notificationService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalException {
        Optional<User> optional = userRepository.findByEmail(userDTO.getEmail().trim());
        if(optional.isPresent()) throw new JobPortalException("USER_FOUND");
        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail(), userDTO.getName()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userDTO.toEntity();
        user = userRepository.save(user);
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalException {
        User user = userRepository.findByEmail(loginDTO.getEmail().trim()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) throw new JobPortalException("INVALID_CREDENTIALS");
        return user.toDTO();
    }
    @Override
    public Boolean sendOtp(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        MimeMessage mm = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("Your OTP Code for Job portal - Job For Real");
        String genOtp = Utilities.generateOTP();
        OTP otp = new OTP(email, genOtp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.getMsgBody(genOtp, user.getName()), true);
        mailSender.send(mm);
        return true;
    }
    @Override
    public Boolean verifyOTP(String email, String otp) throws JobPortalException {
        OTP otpEntity = otpRepository.findById(email).orElseThrow(()-> new JobPortalException("OTP_NOT_FOUND"));

        if(!otpEntity.getOtpCode().equals(otp)) throw new JobPortalException("OTP_INCORRECT");
        return true;
    }
    @Override
    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalException {
        User user= userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        NotificationDTO notificationDto = new NotificationDTO();
        notificationDto.setUserId(user.getId());
        notificationDto.setMessage("Password Reset Successful");
        notificationDto.setAction("Password Reset");
        notificationService.sendNotification(notificationDto);
        return new ResponseDTO("Password changed successfully");
    }
    @Scheduled(fixedRate = 60000) // to execute every 1 minute
    public void removeExpiredOTPs() {

        LocalDateTime expiry = LocalDateTime.now().minusMinutes(5);
        List<OTP> expiredOTPs = otpRepository.findByCreationTimeBefore(expiry);
        if (!expiredOTPs.isEmpty()) {
            otpRepository.deleteAll(expiredOTPs);
            System.out.println("removed: " + expiredOTPs.size() + " expired OTPs");
        }
    }
}
