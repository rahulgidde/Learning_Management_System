package com.bridgelabz.lmsapplication.configuration;

import com.bridgelabz.lmsapplication.filter.JwtRequestFilter;
import com.bridgelabz.lmsapplication.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserServiceImpl jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/user/authenticate", "/user/registeruser", "/user/sendemail",
                "/user/resetpassword", "/hirecandidate/loadhiredcandidates", "/hirecandidate/hiredcandidatelist",
                "/hirecandidate/hiredcandidateprofile", "/hirecandidate/updatecandidatestatus", "/hirecandidate/sendemail",
                "/fellowship/fellowshipcandidatesdata", "/fellowship/fellowshipcandidatecount", "/fellowship/sendjoboffer",
                "/fellowship/updatepersonalnfo", "/bank/updatebankinfo", "/qualification/updateeducationalinfo",
                "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**").permitAll().
                anyRequest().authenticated().and().
                exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper;
    }

    @PostConstruct
    public static void initMessageSourcesAccessor() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public static MessageSourceAccessor getMessageAccessor() {
        return messageSourceAccessor;
    }
}
