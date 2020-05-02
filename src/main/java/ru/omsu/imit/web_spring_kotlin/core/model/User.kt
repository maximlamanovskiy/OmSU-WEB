package ru.omsu.imit.web_spring_kotlin.core.model

import com.fasterxml.jackson.annotation.JsonIgnore

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.FetchType
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.ManyToMany
import javax.persistence.JoinTable
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
@Table(name = "USERS")
open class User(
        @Id open val id: String,
        @Column(name = "username", nullable = false) open val username: String,
        @JsonIgnore @Column(name = "password", nullable = false) open val password: String,
        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "ROLE_FOR_USER",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
        open val roles: Set<Role> = mutableSetOf(),
        @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        open val messages: Set<Message> = mutableSetOf()
) {
    constructor(username: String, password: String) : this(UUID.randomUUID().toString(), username, password)
    constructor(username: String, password: String, roles: Set<Role>) : this(UUID.randomUUID().toString(), username, password, roles)
    constructor(_user: User, messages: Set<Message>) : this(_user.id, _user.username, _user.password, _user.roles, messages)
    constructor() : this("", "", "")
}