package ru.omsu.imit.web_spring_kotlin.web.controller.user.v2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import ru.omsu.imit.web_spring_kotlin.web.model.user.request.RegistrationModel
import ru.omsu.imit.web_spring_kotlin.web.model.user.response.Member
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService

@Controller
@RequestMapping("/v2")
class SignControllerV2
@Autowired
constructor(private val userService: IUserService) {
    @RequestMapping(value = ["/signUp"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun registerUser(@RequestBody @Valid registrationModel: RegistrationModel): ResponseEntity<Member> {
        return ResponseEntity.ok(Member(userService.createUser(registrationModel)))
    }
}