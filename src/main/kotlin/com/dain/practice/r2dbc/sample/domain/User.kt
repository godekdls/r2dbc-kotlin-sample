package com.dain.practice.r2dbc.sample.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("USER")
data class User(
        @Id val id: Long?,
        val name: String,
        val age: Int
)