package com.bridgelabz.lmsapplication.service;

import com.bridgelabz.lmsapplication.dto.EmailDto;
import com.bridgelabz.lmsapplication.dto.UserDto;
import com.bridgelabz.lmsapplication.exception.UserException;
import com.bridgelabz.lmsapplication.model.JwtRequest;
import com.bridgelabz.lmsapplication.model.UserDetailModel;
import com.bridgelabz.lmsapplication.repository.UserRepository;
import com.bridgelabz.lmsapplication.util.IRabbitMQ;
import com.bridgelabz.lmsapplication.util.JwtTokenUtil;
import com.bridgelabz.lmsapplication.util.RedisUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserDetailsService, IUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @Autowired
    private EmailDto emailDto;

    @Autowired
    private RedisUtil redisUtil;

    @Value("spring.redis.key")
    private String rediskey;

    /**
     * METHOD FOR FIND RECORD FORM REPOSITORY BY USERNAME
     *
     * @param username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetailModel user = repository.findByUsername(username)
                .orElseThrow(() -> new UserException(UserException.exceptionType.User_Not_FOUND, "User Not Found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    /**
     * METHOD FOR LOAD USER DETAILS
     *
     * @param userDto
     * @return UserDetailModel
     */
    @Override
    public UserDetailModel loadUserDetails(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserDetailModel user = mapper.map(userDto, UserDetailModel.class);
        return repository.save(user);
    }

    /**
     * METHOD FOR REST PASSWORD
     *
     * @param token
     * @param password
     * @return UserDetailModel
     */
    @Override
    public UserDetailModel resetPassword(String token, String password) {
        String id = jwtTokenUtil.getUsernameFromToken(token);
        return repository.findById(Long.valueOf(id))
                .map(userDetailModel -> {
                    userDetailModel.setPassword(passwordEncoder.encode(password));
                    return userDetailModel;
                })
                .map(repository::save)
                .orElseThrow(() -> new UserException(UserException.exceptionType.User_Not_FOUND, "User Not Found"));
    }

    /**
     * METHOD FOR SEND EMAIL
     *
     * @param emailId
     * @return true
     */
    @Override
    public boolean sendEmail(String emailId) {
        UserDetailModel user = repository.findByEmail(emailId)
                .orElseThrow(() -> new UserException(UserException.exceptionType.INVALID_EMAIL_ID, "EmailId Not Found"));
        final String token = jwtTokenUtil.generateEmailToken(user.getId());
        emailDto.setEmailId(emailId);
        emailDto.setSubject("Forget Password");
        emailDto.setBody(token);
        rabbitMQ.sendMessageToQueue(emailDto);
        return true;
    }

    /**
     * METHOD FOR USER AUTHENTICATION
     *
     * @param authenticationRequest
     * @return token
     * @throws Exception
     */
    @Override
    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        redisUtil.save(rediskey, authenticationRequest.getUsername(), token);
        return token;
    }

    /**
     * METHOD FOR CHECK USER AUTHENTICATION
     *
     * @param username
     * @param password
     * @throws Exception
     */
    @Override
    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
