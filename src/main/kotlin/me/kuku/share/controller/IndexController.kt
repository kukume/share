package me.kuku.share.controller

import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.thymeleaf.*
import io.ktor.server.util.*
import me.kuku.share.entity.DataService

fun Application.index() {
    routing {
        get {
            call.respondTemplate("share/index")
        }

        get("{password}") {
            val password = call.parameters.getOrFail("password")
            DataService.findByPassword(password) ?: throw NotFoundException()
            call.respondTemplate("share/index", mapOf("password" to password))
        }

        get("{password}/preview") {
            val password = call.parameters.getOrFail("password")
            val entity = DataService.findByPassword(password) ?: throw NotFoundException()
            call.respondText(entity.text)
        }
    }
}