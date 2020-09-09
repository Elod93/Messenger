package org.messenger.Configuration;

import org.messenger.Service.CustomOidcUserService;
import org.messenger.Service.UserService;
import org.messenger.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;



@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecConfig extends WebSecurityConfigurerAdapter {

    private CustomOidcUserService oidcUserService;

    @Autowired
    public WebSecConfig(CustomOidcUserService oidcUserService) {
        this.oidcUserService = oidcUserService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login_page")
                .defaultSuccessUrl("/messages")
                .permitAll()
                .and()
                .oauth2Login().userInfoEndpoint()
                .oidcUserService(oidcUserService).and()
                .defaultSuccessUrl("/messages", true)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/rest/csrf", "/messages/{id}", "/rest/messages", "/messages", "/user_info", "/one_message/{userId}", "/greeting", "/registration", "/static/**", "/img/**", "/css/**", "/add_topic").permitAll()
                .anyRequest().authenticated()
                .and().logout().invalidateHttpSession(true)
                .clearAuthentication(true).logoutSuccessUrl("/login_page").deleteCookies("JSESSIONID").permitAll().and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

  /*  public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/satatic/**")) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }
/*
    @Override public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
    }

 */


    @Bean
    public UserDetails userDetailsService(SecurityUser securityUser) {
        UserService userService = new UserService();
        return userService.loadUserByUsername(securityUser.getName());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
   @Autowired
   UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

*/
}




