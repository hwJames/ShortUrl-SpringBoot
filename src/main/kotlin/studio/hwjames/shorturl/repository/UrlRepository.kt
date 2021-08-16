package studio.hwjames.shorturl.repository

import org.springframework.data.mongodb.repository.MongoRepository
import studio.hwjames.shorturl.model.Url

interface UrlRepository : MongoRepository<Url, String> {
    fun findFirstByShortUrl(shortUrl: String) : Url?
}
