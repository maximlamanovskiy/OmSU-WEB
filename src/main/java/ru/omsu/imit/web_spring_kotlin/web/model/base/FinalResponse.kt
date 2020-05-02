package ru.omsu.imit.web_spring_kotlin.web.model.base

open class FinalResponse<T>(open val data: T, open val success: Boolean)