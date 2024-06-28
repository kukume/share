package me.kuku.share.controller

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import me.kuku.share.entity.DataEntity
import me.kuku.share.entity.DataService
import me.kuku.utils.MD5Utils
import org.springframework.stereotype.Component

@Component
class ApiController(
    private val dataService: DataService
) {

    fun Routing.api() {
        route("api") {

            get {
                val password = call.request.queryParameters.getOrFail("password")
                val md5 = MD5Utils.toMD5(password)
                val dataEntity = dataService.findByPassword(md5) ?: error("not found")
                call.respond(dataEntity)
            }

            post {
                val saveParam = call.receive<SaveParam>()
                val password = saveParam.password
                val dataEntity = dataService.findByPassword(password) ?: DataEntity()
                dataEntity.language = saveParam.language
                dataEntity.text = saveParam.text
                dataEntity.password = saveParam.password
                val newEntity = dataService.save(dataEntity)
                call.respond(newEntity)
            }

            delete {
                val deleteParam = call.receive<DeleteParam>()
                dataService.deleteByPassword(MD5Utils.toMD5(deleteParam.password))
                call.respond(HttpStatusCode.NoContent)
            }

        }
    }

}

data class SaveParam(val language: String, val text: String, val password: String)

data class DeleteParam(val password: String)