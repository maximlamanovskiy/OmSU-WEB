package ru.omsu.imit.web_spring_kotlin.core.repository

import org.springframework.data.repository.CrudRepository

import ru.omsu.imit.web_spring_kotlin.core.model.Message

interface MessageRepository : CrudRepository<Message, String>