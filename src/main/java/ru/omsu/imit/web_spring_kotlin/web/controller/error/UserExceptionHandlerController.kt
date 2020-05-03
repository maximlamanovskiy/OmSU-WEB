package ru.omsu.imit.web_spring_kotlin.web.controller.error

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice

import org.springframework.ui.Model
import org.springframework.validation.BindException
import ru.omsu.imit.web_spring_kotlin.web.model.user.response.Member
import ru.omsu.imit.web_spring_kotlin.web.model.user.response.RoleResponse
import ru.omsu.imit.web_spring_kotlin.web.service.role.IRoleService
import javax.servlet.http.HttpServletRequest

import ru.omsu.imit.web_spring_kotlin.web.service.user.IUserService
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.RoleNotFoundException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.UserAlreadyExistsException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.PasswordsDoesNotMatchException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.InvalidUpdateUserRequestException
import ru.omsu.imit.web_spring_kotlin.web.service.user.exception.UserNotFoundException

@ControllerAdvice("ru.omsu.imit.web_spring_kotlin.web.controller.user.v1")
open class UserExceptionHandlerController
@Autowired
constructor(
        private val userService: IUserService,
        private val roleService: IRoleService
) {
    @ExceptionHandler(BindException::class)
    fun handleUserValidationExceptions(req: HttpServletRequest, ex: BindException, model: Model): String {
        ex.fieldErrors.forEach { f -> model.addAttribute("${f.field}Error", f.defaultMessage) }
        var res = "SignUpPage"
        if (req.requestURI.startsWith("/users")) {
            model.addAttribute("member", Member(userService.getUserById(req.requestURI.split("/")[2])))
            model.addAttribute("roles", roleService.getAllRole().map { role -> RoleResponse(role) })
            res = "EditUserPage"
        }
        return res
    }

    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(req: HttpServletRequest, ex: UserAlreadyExistsException, model: Model): String {
        model.addAttribute("usernameError", ex.message)
        var res = "SignUpPage"
        if (req.requestURI.startsWith("/users")) {
            model.addAttribute("member", Member(userService.getUserById(req.requestURI.split("/")[2])))
            model.addAttribute("roles", roleService.getAllRole().map { role -> RoleResponse(role) })
            res = "EditUserPage"
        }
        return res
    }

    @ExceptionHandler(PasswordsDoesNotMatchException::class)
    fun handlePasswordsDoesNotMatchException(req: HttpServletRequest, ex: PasswordsDoesNotMatchException, model: Model): String {
        model.addAttribute("passwordError", ex.message)
        model.addAttribute("confirmPasswordError", ex.message)
        var res = "SignUpPage"
        if (req.requestURI.startsWith("/users")) {
            model.addAttribute("member", Member(userService.getUserById(req.requestURI.split("/")[2])))
            model.addAttribute("roles", roleService.getAllRole().map { role -> RoleResponse(role) })
            res = "EditUserPage"
        }
        return res
    }

    @ExceptionHandler(RoleNotFoundException::class)
    fun handleRoleNotFoundException(req: HttpServletRequest, ex: RoleNotFoundException, model: Model): String {
        model.addAttribute("rolesError", ex.message)
        var res = "SignUpPage"
        if (req.requestURI.startsWith("/users")) {
            model.addAttribute("member", Member(userService.getUserById(req.requestURI.split("/")[2])))
            model.addAttribute("roles", roleService.getAllRole().map { role -> RoleResponse(role) })
            res = "EditUserPage"
        }
        return res
    }

    @ExceptionHandler(InvalidUpdateUserRequestException::class)
    fun handleInvalidUpdateUserRequestException(req: HttpServletRequest, ex: InvalidUpdateUserRequestException, model: Model): String {
        model.addAttribute("rolesError", ex.message)
        var res = "SignUpPage"
        if (req.requestURI.startsWith("/users")) {
            model.addAttribute("member", Member(userService.getUserById(req.requestURI.split("/")[2])))
            model.addAttribute("roles", roleService.getAllRole().map { role -> RoleResponse(role) })
            res = "EditUserPage"
        }
        return res
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(req: HttpServletRequest, ex: UserNotFoundException, model: Model): String {
        model.addAttribute("error", ex.message)
        var res = "SignUpPage"
        if (req.requestURI.startsWith("/users")) {
            model.addAttribute("roles", roleService.getAllRole().map { role -> RoleResponse(role) })
            res = "EditUserPage"
        }
        return res
    }
}