package java12.projectsalemanagement.sercurity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.login.service.ServiceLogin;
import java12.projectsalemanagement.sercurity.dto.LoginDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);

    private ServiceLogin serviceLogin;
    public AuthController(ServiceLogin serviceLogin) {
    	this.serviceLogin = serviceLogin;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);


        try {
        	
        	return serviceLogin.checkLogin(dto);

        } catch (Exception e) {
            logger.debug("{} has been logged in with wrong password: {}",dto.getUsername(), e.getMessage() );
        }

        return ResponseHandler.getResponse("Username or password is invalid.", HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/logout")
    public void logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return ResponseHandler.getResponse(HttpStatus.OK);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

           new SecurityContextLogoutHandler().logout(request, response, auth);

    }
}
