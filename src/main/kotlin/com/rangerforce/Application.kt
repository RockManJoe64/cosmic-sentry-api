package com.rangerforce

import io.github.cdimascio.dotenv.dotenv
import io.micronaut.runtime.Micronaut.run

fun main(args: Array<String>) {
	val dotenv = dotenv()
	for (e in dotenv.entries()) {
		System.setProperty(e.key, e.value)
	}
	run(*args)
}
