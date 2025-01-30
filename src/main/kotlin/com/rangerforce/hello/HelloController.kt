package com.rangerforce.hello

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import jakarta.validation.constraints.NotBlank

@Controller("/hello")
open class HelloController {

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    fun index() = "Hello World"

    @Get("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    open fun greet(@NotBlank name: String) = "Hello $name"

    @Post("/echo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun echo(@Body message: Message) = message
}