package com.alexandru.springbootecommerce.service;

import com.alexandru.springbootecommerce.dto.AuthenticatedUserDetails;
import com.alexandru.springbootecommerce.dto.LoginRequest;
import com.alexandru.springbootecommerce.dto.RegistrationRequest;
import com.alexandru.springbootecommerce.dto.UserDto;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface UserService {

    void registerUser(RegistrationRequest request, String siteURL) throws MessagingException, UnsupportedEncodingException;
    AuthenticatedUserDetails loginUser(LoginRequest request);
    void checkAvailabilityOfEmail(String username);
    boolean confirmRegistration(String verificationCode);
    String getSiteURL(HttpServletRequest request);
}
