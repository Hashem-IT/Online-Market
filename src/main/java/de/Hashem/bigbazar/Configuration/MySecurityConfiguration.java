package de.Hashem.bigbazar.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * Klasse für Sicherheit über Webseiten 
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("labresources")
    private UserDetailsService userSecurityService;

    @Autowired
    private MySecuritiyUtilities securitiyUtilities;

    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return securitiyUtilities.bCryptPasswordEncoder();
    }

    private static final String[] ALLOW_ACCESS_WITHOUT_AUTHENTICATION = {
            "static/**","/css/**", "/image/**", "/fonts/**", "/", "/login", "/forgotPassword", "/register", "/h2-console/**", "/registrieren","/produktAdd","/Ergebnis", "/kundenkonto.html", "/productsearch/**", "/restapi/**" };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ALLOW_ACCESS_WITHOUT_AUTHENTICATION)
                .permitAll().anyRequest().authenticated()
                .and().formLogin()
                .and().csrf().ignoringAntMatchers( "/h2-console/**", "/restapi/**" )
                .and().headers().frameOptions().sameOrigin();
        http
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout")
                .deleteCookies("remember-me")
                .permitAll()
                .and()
                .rememberMe();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService)
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
