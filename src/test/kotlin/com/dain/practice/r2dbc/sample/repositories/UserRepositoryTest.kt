package com.dain.practice.r2dbc.sample.repositories

import com.dain.practice.r2dbc.sample.config.ReactiveH2Config
import com.dain.practice.r2dbc.sample.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.kotlin.test.test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = [ReactiveH2Config::class])
class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun findFirstByName() {
        userRepository.findFirstByName("tory").test()
            .expectNextCount(1)
            .verifyComplete()
    }

    @Test
    fun save() {
        User(null, "new user", 22)
            .run { userRepository.save(this) }
            .test()
            .assertNext {
                assertThat(it.id).isNotNull()
            }
            .verifyComplete()
    }
}