package java12.projectsalemanagement.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//    @Autowired
//    @Qualifier("userDetailsServiceImpl")
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtAuthorizationFilter jwtAuthorizationFilter;
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(getPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // cấu hình CORS
//        http.cors();
//
//        // cấu hình session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // thêm filter để validate jwt token
//        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        // disable csrf
//        http.csrf().disable();
//
//        // cấu hình xác thực cho các api
//        http.antMatcher("/api/**").authorizeRequests()
//                .antMatchers("/api/auth/login").permitAll()
//                .anyRequest().authenticated();
//
//    }
}
