package com.socialhoaxify.wsfs.configuration;

import com.socialhoaxify.wsfs.services.UserAuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// this class is created for spring security config
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{

    private final UserAuthService userAuthService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.httpBasic().authenticationEntryPoint(new AuthEntryPoint());
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/api/1.0/auth").authenticated()
                .and()
                .authorizeRequests().anyRequest().permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //securityle ilgili bir session create yapmıyor.
        // Normalde bir user bilgini alıyor ve sanki o hep var gibi işlem yapıyordu
    }


    //eğer bir user aranacaksa benim bu servisimi kullan ve kullancıı var mı yok mu kotnrol et
    //Burada password encoder ile şifreyi oluştjurup spring security'de bunu haber veriyoruz
    //daha önce AuthController da yaptık
        protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder());

        }



}
