package ru.omsu.imit.web_spring_kotlin.web.controller.user.v1

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

import org.springframework.http.MediaType
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod

import ru.omsu.imit.web_spring_kotlin.web.model.user.response.Member
import ru.omsu.imit.web_spring_kotlin.web.model.user.request.UpdateUserModel
import ru.omsu.imit.web_spring_kotlin.web.model.user.response.RoleResponse
import ru.omsu.imit.web_spring_kotlin.web.service.role.IRoleService
import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService

@Controller
@RequestMapping("/users")
open class UserController
@Autowired
constructor(
        private val userService: IUserService,
        private val roleService: IRoleService
) {
    @Transactional
    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    open fun getAllUsers(model: Model): String {
        val members: List<Member> = userService.getAllUsers().map { user -> Member(user) }
        model.addAttribute("members", members)
        return "UsersPage"
    }

    @Transactional
    @RequestMapping(path = ["/{id}"], method = [RequestMethod.GET], produces = [MediaType.TEXT_HTML_VALUE])
    open fun getUser(@PathVariable("id") userId: String, model: Model): String {
        val member = Member(userService.getUserById(userId))
        model.addAttribute("member", member)
        model.addAttribute("userId", member.id)
        model.addAttribute("roles", roleService.getAllRole().map { role -> RoleResponse(role) })
        return "EditUserPage"
    }

    @Transactional
    @RequestMapping(path = ["/{id}"], method = [RequestMethod.POST], produces = [MediaType.TEXT_HTML_VALUE])
    open fun updateUser(@PathVariable("id") userId: String, @Valid @ModelAttribute updateUserModel: UpdateUserModel): String {
        userService.updateUser(userId, updateUserModel)
        return "redirect:/users"
    }
}