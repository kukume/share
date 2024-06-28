package me.kuku.share.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Service

@Document("data")
class DataEntity {
    @Id
    var id: String? = null
    @Indexed(unique = true)
    var password: String = ""
    var text: String = ""
    var language: String = ""
}

interface DataRepository: CoroutineCrudRepository<DataEntity, String> {

    suspend fun findByPassword(password: String): DataEntity?

    suspend fun deleteByPassword(password: String)

}

@Service
class DataService(
    private val dataRepository: DataRepository
) {
    suspend fun save(dataEntity: DataEntity): DataEntity = dataRepository.save(dataEntity)

    suspend fun findByPassword(password: String) = dataRepository.findByPassword(password)

    suspend fun deleteByPassword(password: String) = dataRepository.deleteByPassword(password)

}