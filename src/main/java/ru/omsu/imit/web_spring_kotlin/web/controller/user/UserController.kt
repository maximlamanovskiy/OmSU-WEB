package ru.omsu.imit.web_spring_kotlin.web.controller.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.core.repository.UserRepository

@Controller
@RequestMapping("/users")
class UserController
@Autowired constructor(
        private val userRepository: UserRepository
) {
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getAllUsers(): ResponseEntity<List<User>> {
        val body: List<User> = userRepository.findAll().toList()
        return ResponseEntity.ok(body)
    }
}