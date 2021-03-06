package ru.omsu.imit.web_spring_kotlin.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import ru.omsu.imit.web_spring_kotlin.core.service.user.SimpleUserDetailsService

@Configuration
@EnableWebSecurity
open class WebSecurityConfig
@Autowired
constructor(
        private val userDetailsService: SimpleUserDetailsService
) : WebSecurityConfigurerAdapter() {
    @Bean
    open fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    open fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http.authorizeRequests().antMatchers("/", "/logout", "/whoAmI", "/signIn", "/signUp").permitAll()

        http.authorizeRequests().antMatchers("/whoAmI", "/chat").hasAuthority("USER")
        http.authorizeRequests().antMatchers("/users/**").hasAuthority("ADMIN")

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403")

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/signIn")
                .defaultSuccessUrl("/whoAmI")
                .failureUrl("/signIn?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/signOut").logoutSuccessUrl("/signOutSuccess")
    }
}
