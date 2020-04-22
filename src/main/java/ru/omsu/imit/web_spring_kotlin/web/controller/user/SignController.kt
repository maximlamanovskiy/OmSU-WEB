package ru.omsu.imit.web_spring_kotlin.web.controller.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod

import ru.omsu.imit.web_spring_kotlin.web.model.user.RegistrationModel
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService

@Controller
class SignController
@Autowired
constructor(private val userService: IUserService){
    @RequestMapping(value = ["/signIn"], method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    fun getLoginPage(model: Model): String {
        return "signInPage"
    }
    @RequestMapping(value = ["/signUp"], method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    fun getRegisterPage(): String {
        return "SignUpPage"
    }

    @RequestMapping(value = ["/signUp"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE], produces = [MediaType.TEXT_HTML_VALUE])
    fun registerUser(@Valid @ModelAttribute registrationModel: RegistrationModel, model: Model): String {
        model.addAttribute("newUser", userService.createUser(registrationModel))
        return "SignUpSuccessPage"
    }
}