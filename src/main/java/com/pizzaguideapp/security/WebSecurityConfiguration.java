package com.pizzaguideapp.security;


import com.pizzaguideapp.security.jwt.AuthEntryPoint;
import com.pizzaguideapp.security.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        	http.cors().and().csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN") //get all users - admin
                    .antMatchers(HttpMethod.POST, "/users", "/auth/signin", "/auth/signup").permitAll() //register and auth user
                    .antMatchers(HttpMethod.GET, "/recipes").permitAll() //get all recipes
                    .antMatchers(HttpMethod.POST, "/recipes").hasAnyRole("ADMIN", "USER") //save recipe
                    .antMatchers(HttpMethod.GET, "/steps").hasRole("ADMIN") //get all steps - only admin
                    .antMatchers(HttpMethod.POST, "/recipes/{id}/steps").hasAnyRole("ADMIN", "USER") //save step for recipe
                    .antMatchers(HttpMethod.POST, "/recipes/{id}/media").hasAnyRole("ADMIN", "USER") //save media for recipe
                    .antMatchers(HttpMethod.GET, "/reviews", "/recipes/{id}/reviews").hasAnyRole("ADMIN", "USER") //get reviews for user and for recipe
                    .antMatchers(HttpMethod.POST, "/recipes/{id}/reviews").hasAnyRole("ADMIN", "USER") //save review for recipe
                    .antMatchers(HttpMethod.GET, "/ingredients").hasAnyRole("ADMIN", "USER") //get all ingredients
                    .antMatchers(HttpMethod.POST, "/ingredients").hasAnyRole("ADMIN", "USER") //save ingredient
                    .antMatchers(HttpMethod.GET, "/recipes/{id}/components").hasAnyRole("ADMIN", "USER") //get all components for recipe
                    .anyRequest().authenticated();

            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
