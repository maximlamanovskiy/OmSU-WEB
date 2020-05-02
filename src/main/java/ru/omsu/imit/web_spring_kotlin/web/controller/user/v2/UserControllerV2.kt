package ru.omsu.imit.web_spring_kotlin.web.controller.user.v2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMethod

import ru.omsu.imit.web_spring_kotlin.web.model.user.response.Member
import ru.omsu.imit.web_spring_kotlin.web.model.user.request.UpdateUserModel
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService

@Controller
@RequestMapping("/v2/users")
open class UserControllerV2
@Autowired
constructor(
        private val userService: IUserService
) {
    @Transactional
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    open fun getAllUsers(): ResponseEntity<List<Member>> {
        return ResponseEntity.ok(userService.getAllUsers().map { user -> Member(user) })
    }

    @Transactional
    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    open fun getUser(@PathVariable("id") userId: String): ResponseEntity<Member> {
        return ResponseEntity.ok(Member(userService.getUserById(userId)))
    }

    @Transactional
    @RequestMapping(path = ["/{id}"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    open fun updateUser(@PathVariable("id") userId: String, @Valid @RequestBody updateUserModel: UpdateUserModel): ResponseEntity<Member> {
        return ResponseEntity.ok(Member(userService.updateUser(userId, updateUserModel)))
    }
}