package Application.config;

import Application.entities.UserEntity;
import Application.repo.UserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import static Application.entities.UserEntity.UserRole.AUTHORISED;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login")                                     // for this requests not need authenticated, any other should be.
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository){
        return map -> {
            String id = (String) map.get("sub");
            Integer iid = Integer.parseInt(id);
            UserEntity user = userRepository.findById(iid).orElseGet(() -> {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(iid);
                userEntity.setNickname((String) map.get("name"));
                userEntity.setFirstName((String) map.get("name"));
                userEntity.setLastName((String) map.get("name"));
                userEntity.setEMail("email");

                userEntity.setRole(AUTHORISED);
                return userEntity;
            });
            return userRepository.saveAndFlush(user);
        };
    }
}
