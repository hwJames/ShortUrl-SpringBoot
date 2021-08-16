package studio.hwjames.shorturl.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "urls")
data class Url (
    @Id
    var _id: ObjectId = ObjectId.get(),
    val defaultUrl: String,
    val shortUrl: String,
    val createDate: LocalDateTime = LocalDateTime.now(),
    val updateDate: LocalDateTime = LocalDateTime.now()
)