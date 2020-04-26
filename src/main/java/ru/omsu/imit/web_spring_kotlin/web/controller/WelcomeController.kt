package ru.omsu.imit.web_spring_kotlin.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/")
class WelcomeController {
    @RequestMapping(method = [RequestMethod.GET])
    fun getWelcomePage(model: Model): String {
        model.addAttribute("title", "Welcome")
        model.addAttribute("message", "This is welcome page!")
        return "WelcomePage"
    }
}