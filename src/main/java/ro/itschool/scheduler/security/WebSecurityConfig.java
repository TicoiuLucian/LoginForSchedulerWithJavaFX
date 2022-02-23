package ro.itschool.scheduler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import ro.itschool.scheduler.service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

//    public DaoAuthenticationProvider authProvider(CustomUserDetailsService userDetailsService) {
//        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(getPasswordEncoder());
//        return authProvider;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("**/**")
                .authenticated()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/user/all").hasRole("ADMIN").and().httpBasic();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return BCrypt.hashpw(charSequence.toString(), BCrypt.gensalt(4));
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return BCrypt.checkpw(charSequence.toString(), s);
            }
        };
    }
}