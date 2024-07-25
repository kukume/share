package me.kuku.share

import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.cio.*
import io.ktor.server.config.yaml.*

fun main(args: Array<String>) = EngineMain.main(args)


private val yamlConfig = YamlConfig(null)!!
val mongoConfig = yamlConfig.config("ktor.mongo")

private val mongoClient = MongoClient.create(mongoConfig.property("uri").getString())

val mongoDatabase = mongoClient.getDatabase(mongoConfig.property("database").getString())