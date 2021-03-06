package com.emmanuel.escalade.config;

import com.emmanuel.escalade.Services.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Gestion de la configuration de la sécurité du site escalade
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailServiceImpl userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailServiceImpl userDetailServiceImpl) {
        this.userDetailsService = userDetailServiceImpl;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    /**
     * Définit l'ensemble des paramètres de sécurité du site : URL, répertoires authorisées, restrictions d'accès,
     * attributs de l'utilisateur utilisés au login, page de login, gestion du cookie de session
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/nouvelutilisateur", "/listetopos", "/listesites", "/site/*",
                        "/recherchersites", "/css/**", "/img/**","/webjars/**").permitAll()
                .antMatchers("/topo").hasAuthority("user")
                .antMatchers("/modifiercommentaire/*", "supprimercommentaire/*").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("pseudo")
                    .passwordParameter("motDePasse")
                 //   .successForwardUrl("/")
                    .failureForwardUrl("/login-error")
                    .permitAll()
                .and()
                    .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
          //    .and().rememberMe()
        ;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
