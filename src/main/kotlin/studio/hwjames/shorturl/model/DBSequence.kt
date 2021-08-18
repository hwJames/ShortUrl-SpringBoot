package studio.hwjames.shorturl.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "db_sequence")
data class DBSequence (

    @Id
    val id: String,
    val seq: Int,
)