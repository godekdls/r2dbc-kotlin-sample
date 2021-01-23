package com.dain.practice.r2dbc.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class R2dbcSampleApplication

fun main(args: Array<String>) {
	runApplication<R2dbcSampleApplication>(*args)
}
