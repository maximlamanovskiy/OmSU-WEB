package ru.omsu.imit.web_spring_kotlin.web.service.user.exception

import java.lang.Exception

class UserAlreadyExistsException(message: String) : Exception(message)
