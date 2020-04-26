package ru.omsu.imit.web_spring_kotlin.web.controller.interceptor

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AdminActionsInterceptor(file: String) : HandlerInterceptorAdapter() {
    companion object AdminActionLogger {
        val logger: Logger = Logger.getLogger("AdminActionLogger")
    }

    init {
        val fileHandler = FileHandler(file)
        fileHandler.formatter = SimpleFormatter()
        logger.addHandler(fileHandler)
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info("Method ${request.method} was invoked on URI: ${request.requestURI}")
        return true
    }
}