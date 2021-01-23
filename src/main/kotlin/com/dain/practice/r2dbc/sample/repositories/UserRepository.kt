package com.dain.practice.r2dbc.sample.repositories

import com.dain.practice.r2dbc.sample.domain.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserRepository : ReactiveCrudRepository<User, Long> {
    fun findFirstByName(name: String) : Mono<User>
}