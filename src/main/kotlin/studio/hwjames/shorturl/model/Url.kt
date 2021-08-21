package studio.hwjames.shorturl.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "urls")
data class Url(

    @Id
    var id: Int,
    val defaultUrl: String,

    val createDate: LocalDateTime = LocalDateTime.now(),
    var readDate: LocalDateTime? = null,
    var readCnt: Int = 0
)