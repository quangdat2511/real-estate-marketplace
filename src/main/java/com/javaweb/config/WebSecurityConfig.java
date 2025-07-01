package com.javaweb.config;

import com.javaweb.security.CustomSuccessHandler;
import com.javaweb.service.impl.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable()
                .authorizeRequests()
                        .antMatchers(HttpMethod.DELETE, "/api/buildings/{ids}").hasRole("MANAGER")
                        .antMatchers(HttpMethod.DELETE, "/api/customers/{ids}").hasRole("MANAGER")
                        .antMatchers(HttpMethod.DELETE, "/api/transactions/{ids}").hasRole("MANAGER")
                        .antMatchers("/api/customers/*/staffs").hasRole("MANAGER")
                        .antMatchers("/api/buildings/*/staffs").hasRole("MANAGER")
                        .antMatchers("/api/assign/**").hasRole("MANAGER")
                        .antMatchers("api/user", "api/user/**").hasRole("MANAGER")
                        .antMatchers("admin/user-list", "admin/user-edit", "admin/user-edit-{id}", "/admin/profile-{username}", "/admin/profile-password").hasRole("MANAGER")
                        .antMatchers("/api/buildings/{id}/staffs").hasRole("MANAGER")
                        .antMatchers("api/customers", "api/customers/**").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers("api/transactions", "api/transactions/**").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers("api/buildings", "api/buildings/**").hasAnyRole("MANAGER", "STAFF")
                        .antMatchers("/admin/**").hasAnyRole("MANAGER","STAFF")
                        .antMatchers("/login", "/resource/**", "/trang-chu", "/api/**").permitAll()
                .and()
                .formLogin().loginPage("/login").usernameParameter("j_username").passwordParameter("j_password").permitAll()
                .loginProcessingUrl("/j_spring_security_check")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/login?incorrectAccount").and()
                .logout().logoutUrl("/logout").deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/access-denied").and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login?sessionTimeout");
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new CustomSuccessHandler();
    }
}
