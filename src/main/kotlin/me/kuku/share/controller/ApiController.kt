package me.kuku.share.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import me.kuku.share.entity.DataEntity
import me.kuku.share.entity.DataService
import me.kuku.share.utils.MD5Utils

fun Application.api() {
    routing {
        route("api") {

            get {
                val password = call.request.queryParameters.getOrFail("password")
                val md5 = MD5Utils.toMD5(password)
                val dataEntity = DataService.findByPassword(md5) ?: error("not found")
                call.respond(dataEntity)
            }

            post {
                val saveParam = call.receive<SaveParam>()
                val password = saveParam.password
                val dataEntity = DataService.findByPassword(password) ?: DataEntity()
                dataEntity.language = saveParam.language
                dataEntity.text = saveParam.text
                dataEntity.password = saveParam.password
                val newEntity = DataService.save(dataEntity)
                call.respond(newEntity)
            }

            delete {
                val deleteParam = call.receive<DeleteParam>()
                DataService.deleteByPassword(MD5Utils.toMD5(deleteParam.password))
                call.respond(HttpStatusCode.NoContent)
            }

        }
    }
}

data class SaveParam(val language: String, val text: String, val password: String)

data class DeleteParam(val password: String)