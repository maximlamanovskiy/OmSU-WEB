package ru.omsu.imit.web_spring_kotlin.web.controller.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod
import ru.omsu.imit.web_spring_kotlin.core.model.Role

import ru.omsu.imit.web_spring_kotlin.core.model.User
import ru.omsu.imit.web_spring_kotlin.web.model.user.response.Member
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService

@Controller
@RequestMapping("/users")
open class UserController
@Autowired constructor(
        private val userService: IUserService
) {
    @Transactional
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    open fun getAllUsers(model: Model): String {
        val members: List<Member> = userService.getAllUsers().map { user -> Member(user.username, user.roles) }
        model.addAttribute("members", members)
        return "Users"
    }

    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun getUser(@PathVariable("id") userId: String): ResponseEntity<User> {
        return ResponseEntity.ok(userService.getUserById(userId))
    }
}