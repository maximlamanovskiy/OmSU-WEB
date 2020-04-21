package ru.omsu.imit.web_spring_kotlin.web.controller.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import ru.omsu.imit.web_spring_kotlin.web.model.user.RegistrationModel
import ru.omsu.imit.web_spring_kotlin.web.service.user.SimpleUserService
import javax.validation.Valid

@Controller
@RequestMapping("/register")
class RegisterController
@Autowired
constructor(private val userService: SimpleUserService) {
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    fun getRegisterPage(): String {
        return "registerPage"
    }

    @RequestMapping(method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = [MediaType.TEXT_HTML_VALUE])
    fun registerUser(@Valid @ModelAttribute registrationModel: RegistrationModel, model: Model): String {
        model.addAttribute("newUser", userService.createUser(registrationModel))
        return "registerSuccess"
    }
}