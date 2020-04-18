package ru.omsu.imit.web_spring_kotlin.web.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class SimpleUserDetailsService: UserDetailsService {
    override fun loadUserByUsername(p0: String?): UserDetails {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}