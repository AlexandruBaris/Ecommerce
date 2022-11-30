package com.alexandru.springbootecommerce.controller;

import com.alexandru.springbootecommerce.dto.RegistrationRequest;
import com.alexandru.springbootecommerce.dto.UserDto;
import com.alexandru.springbootecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public HttpStatus registerUser(@RequestBody @Valid RegistrationRequest registrationRequest, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        userService.registerUser(registrationRequest, userService.getSiteURL(request));
        return HttpStatus.CREATED;
    }


    @GetMapping("/verify")
    public HttpStatus verifyUser(@Param("code") String code) {
        return userService.confirmRegistration(code) ?  HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }
}
