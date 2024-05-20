/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.DAPP01Practica05;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author ian
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig{
    
//    @Autowired
//    UsuarioDetailService usuarioServicio;
//    
//    @Bean
//    PasswordEncoder bcryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//    
//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider(){
//        
//       DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//       daoAuthenticationProvider.setPasswordEncoder(bcryptPasswordEncoder());
//       daoAuthenticationProvider.setUserDetailsService(usuarioServicio);
//       return daoAuthenticationProvider;
//    }
//    
//    protected void configure(HttpSecurity http) throws Exception{
//        http.
//                csrf().disable().
//                authorizeRequests()
//                .antMatchers("/url").permitAll()
//                .anyRequest().authenticated().and().httpBasic();
//    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailService();
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
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            auth -> auth.anyRequest().authenticated()).httpBasic();
         
        return http.build();
    }
}
