package com.example.accessingdatar2dbc

import org.springframework.data.annotation.Id

@Suppress("unused")
data class Customer(
    val firstName: String? = null,
    val lastName: String? = null,
    @Id
    val Id: Long? = null
)
