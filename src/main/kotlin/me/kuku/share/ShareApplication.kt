package me.kuku.share

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing

@SpringBootApplication
@EnableReactiveMongoAuditing
class ShareApplication

fun main(args: Array<String>) {
    runApplication<ShareApplication>(*args)
}