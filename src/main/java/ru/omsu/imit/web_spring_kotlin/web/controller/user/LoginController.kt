package ru.omsu.imit.web_spring_kotlin.web.controller.user

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/login")
class LoginController {
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    fun getLoginPage(model: Model): String {
        return "loginPage"
    }
}