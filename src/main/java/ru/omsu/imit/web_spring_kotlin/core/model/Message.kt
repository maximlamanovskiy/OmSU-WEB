package ru.omsu.imit.web_spring_kotlin.core.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.ManyToOne
import javax.persistence.JoinTable
import javax.persistence.FetchType
import javax.persistence.CascadeType
import javax.persistence.JoinColumn

@Entity
@Table(name = "MESSAGES")
open class Message(
        @Id open val id: String,
        @Column(name = "message", nullable = false) open val message: String,
        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "MESSAGES_FROM_USER",
                joinColumns = [JoinColumn(name = "message_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
        ) open val owner: User = User()
) {
    constructor(message: String, user: User): this(UUID.randomUUID().toString(), message, user)
    constructor(): this("", "")
}