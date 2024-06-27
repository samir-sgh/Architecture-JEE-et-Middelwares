package net.samir.tp3_hospital.security;


import net.samir.tp3_hospital.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
        String EncadedPass=  passwordEncoder.encode("12345");
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(EncadedPass).roles("USER").build(),
                User.withUsername("user2").password(EncadedPass).roles("USER").build(),
                User.withUsername("admin").password(EncadedPass).roles("USER","ADMIN").build()
        );
    }
    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            };
        };
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity httpSecurity, UserDetailsServiceImpl userDetailsServiceImpl) throws Exception {
        return httpSecurity
                .formLogin(ar->ar.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
                .rememberMe(Customizer.withDefaults())
                .authorizeHttpRequests(ar ->ar.requestMatchers("/webjars/**","/h2-console/**").permitAll())
                .exceptionHandling(ar->ar.accessDeniedPage("/notAuthorized"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .userDetailsService(userDetailsServiceImpl)
                .build();
        //.authorizeHttpRequests(ar ->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
        //.authorizeHttpRequests(ar ->ar.requestMatchers("/user/**").hasRole("USER"))

    }
}
