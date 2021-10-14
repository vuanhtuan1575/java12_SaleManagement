package java12.projectsalemanagement.sercurity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.jsonwebtoken.Jwts;
import java12.projectsalemanagement.common.util.ResponseHandler;
import java12.projectsalemanagement.role.entity.Role;
import java12.projectsalemanagement.sercurity.dto.AuthenticationResponse;
import java12.projectsalemanagement.sercurity.dto.LoginDto;
import java12.projectsalemanagement.sercurity.jwt.JwtUtils;
import java12.projectsalemanagement.sercurity.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    //
private final UserDetailsServiceImpl service;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils,UserDetailsServiceImpl service) {
        authenticationManager = authManager;
        this.jwtUtils = jwtUtils;
        this.service=service;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Authentication auth = null;

        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtils.generateJwtToken(auth);
            // log history - AOP
            return ResponseHandler.getResponse(token, HttpStatus.OK);

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
