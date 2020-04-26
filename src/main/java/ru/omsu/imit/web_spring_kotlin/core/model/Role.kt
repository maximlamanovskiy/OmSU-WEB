package ru.omsu.imit.web_spring_kotlin.core.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

import javax.persistence.FetchType
import javax.persistence.CascadeType
import java.util.UUID

@Entity
@Table(name = "ROLE")
open class Role constructor(
        @Id open val id: String,
        @Column(name = "role", nullable = false) open val role: String,
        @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) open val users: Set<User> = mutableSetOf()
) {
    constructor(role: String) : this(UUID.randomUUID().toString(), role)
    constructor() : this("", "")
}