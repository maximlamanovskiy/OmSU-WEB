package ru.omsu.imit.web_spring_kotlin.web.controller.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/whoAmI")
class WhoAmIController {
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    fun getUserInfo(model: Model) {

    }
}