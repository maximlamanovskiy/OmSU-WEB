package ru.omsu.imit.web_spring_kotlin.web.controller.error

import java.security.Principal

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class SimpleErrorsController {
    @RequestMapping(value = ["/403"], method = [RequestMethod.GET])
    fun accessDenied(model: Model, principal: Principal?): String {
        if (principal != null) {
            model.addAttribute("message", "Hi ${principal.name} <br> You don't have a permission to access this page!")
        }
        return "403Page"
    }
}