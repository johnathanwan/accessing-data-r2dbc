package com.example.accessingdatar2dbc

import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

@Suppress("unused")
interface CustomerRepository : ReactiveCrudRepository<Customer, Long> {

    @Query("SELECT * FROM customer WHERE last_name = :lastname")
    fun findByLastName(lastName: String): Flux<Customer>
}