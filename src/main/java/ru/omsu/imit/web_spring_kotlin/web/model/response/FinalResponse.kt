package ru.omsu.imit.web_spring_kotlin.web.model.response

open class FinalResponse<T>(open val data: T, open val success: Boolean)