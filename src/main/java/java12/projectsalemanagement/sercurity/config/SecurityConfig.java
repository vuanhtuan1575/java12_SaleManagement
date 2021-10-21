package java12.projectsalemanagement.sercurity.config;

import java12.projectsalemanagement.sercurity.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // cấu hình CORS
        http.cors();

        // cấu hình session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // thêm filter để validate jwt token
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // disable csrf
        http.csrf().disable();

        // cấu hình xác thực cho các api
        http.antMatcher("/api/**").authorizeRequests()
                .antMatchers("/api/user/create-user").permitAll()
                .antMatchers("/api/auth/authenticate").permitAll()
                .antMatchers("/api/user/getAll-user").permitAll()
                .anyRequest().authenticated();
//                .and()
//                .formLogin() // Cho phép người dùng xác thực bằng form login
//                .defaultSuccessUrl("/hello")
//                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
//                .and()
//                .logout() // Cho phép logout
//                .permitAll();


//        http.antMatcher("api/admin/**")
//                .authorizeRequests()
//                .antMatchers("api/admin/login")
//                .permitAll()
//                .antMatchers("/api/admin/**")
//                .hasAnyRole("ADMIN","USER")
//                .anyRequest().authenticated();



    }
}
