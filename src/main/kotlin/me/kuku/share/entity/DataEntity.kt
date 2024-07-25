package me.kuku.share.entity

import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.ReplaceOptions
import kotlinx.coroutines.flow.firstOrNull
import me.kuku.share.mongoDatabase
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

val dataCollection = mongoDatabase.getCollection<DataEntity>("data")

data class DataEntity(
    @BsonId var id: ObjectId? = null,
    var password: String = "",
    var text: String = "",
    var language: String = ""
)

object DataService {
    suspend fun findByPassword(password: String) = dataCollection.find(eq("password", password)).firstOrNull()
    suspend fun save(entity: DataEntity): DataEntity {
        dataCollection.replaceOne(eq("_id", entity.id), entity, ReplaceOptions().upsert(true))
        return entity
    }
    suspend fun deleteByPassword(password: String) = dataCollection.deleteOne(eq(DataEntity::password.name, password))
}