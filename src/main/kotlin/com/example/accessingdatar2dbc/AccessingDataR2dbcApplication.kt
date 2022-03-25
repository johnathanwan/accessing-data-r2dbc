package com.example.accessingdatar2dbc

import mu.*
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.*
import java.time.*

@SpringBootApplication
class AccessingDataR2dbcApplication {
    companion object {
        val logger = KotlinLogging.logger {}
    }


    @Bean
    fun demo(repository: CustomerRepository): CommandLineRunner {
        return CommandLineRunner {
            /**
             * save a few customers
             */
            @Suppress("SpellCheckingInspection")
            repository.saveAll(
                listOf(
                    Customer("Jack", "Bauer"),
                    Customer("Chloe", "O'Brian"),
                    Customer("Kim", "Bauer"),
                    Customer("David", "Palmer"),
                    Customer("Michelle", "Dessler")
                )
            ).blockLast(Duration.ofSeconds(10))

            /**
             * fetch all customers
             */
            logger.info { "Customers found with findAll():" }
            logger.info { "-------------------------------" }
            repository.findAll().doOnNext { logger.info { it.toString() } }
                .blockLast(Duration.ofSeconds(10))
            logger.info { "" }

            /**
             * fetch an individual customer by ID
             */
            repository.findById(1L).doOnNext {
                logger.info { "Customer found with findById(1L)" }
                logger.info { "--------------------------------" }
                logger.info { it.toString() }
                logger.info { "" }
            }.block(Duration.ofSeconds(10))

            /**
             * fetch customers by last name
             */
            logger.info { "Customer found with findByLastName('Bauer'):" }
            logger.info { "--------------------------------------------" }
            repository.findByLastName("Bauer").doOnNext {
                logger.info { it.toString() }
            }.blockLast(Duration.ofSeconds(10))
            logger.info { "" }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<AccessingDataR2dbcApplication>(*args)
}
