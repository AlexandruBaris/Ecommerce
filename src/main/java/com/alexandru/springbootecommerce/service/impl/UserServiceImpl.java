package com.alexandru.springbootecommerce.service.impl;

import com.alexandru.springbootecommerce.dto.AuthenticatedUserDetails;
import com.alexandru.springbootecommerce.dto.LoginRequest;
import com.alexandru.springbootecommerce.dto.RegistrationRequest;
import com.alexandru.springbootecommerce.dto.UserDto;
import com.alexandru.springbootecommerce.entity.User;
import com.alexandru.springbootecommerce.exceptions.AccountNotActivated;
import com.alexandru.springbootecommerce.exceptions.UserAlreadyExistsException;
import com.alexandru.springbootecommerce.exceptions.UserNotFoundException;
import com.alexandru.springbootecommerce.repository.UserRepository;
import com.alexandru.springbootecommerce.security.jwt.JwtTokenProvider;
import com.alexandru.springbootecommerce.service.UserService;
import com.alexandru.springbootecommerce.entity.Role;
import com.alexandru.springbootecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;

    @Override
    public void registerUser(RegistrationRequest request, String siteURL) throws MessagingException, UnsupportedEncodingException {

        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });

        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Optional<Role> role = roleRepository.findById(1L);
        role.ifPresent(user::addRole);

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        sendVerificationEmail(user, siteURL);

        userRepository.save(user);
    }

    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getUsername();
        String fromAddress = "alex.online.ecommerce@gmail.com";
        String senderName = "Ecommerce";
        String subject = "Please confirm your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "The Ecommerce team.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String fullName = user.getFirstName() + " " + user.getLastName();

        content = content.replace("[[name]]", fullName);
        String verifyURL = siteURL + "/registration/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }


    public boolean confirmRegistration(String verificationCode) {
        Optional<User> user = userRepository.findByVerificationCode(verificationCode);

        if ( user.isEmpty() || user.get().isEnabled()) {
            return false;
        } else {
            user.get().setVerificationCode(null);
            user.get().setEnabled(true);
            userRepository.save(user.get());

            return true;
        }
    }

    @Override
    public AuthenticatedUserDetails loginUser(LoginRequest request) {
        String requestUsername = request.getUsername();
        String requestPassword = request.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestUsername, requestPassword));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()->
                new UserNotFoundException("User with username " + requestUsername + " was not found"));

        if(!user.isEnabled()){
            throw new AccountNotActivated("Your account is not activated. Please check your email");
        }
        String token = jwtTokenProvider.createToken(user);

        return AuthenticatedUserDetails.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .token(token)
                .roles(user.getRoles())
                .build();

    }

    @Override
    public void checkAvailabilityOfEmail(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new UserAlreadyExistsException();
        });
    }

    public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
