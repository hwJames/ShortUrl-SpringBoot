package studio.hwjames.shorturl.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import studio.hwjames.shorturl.model.Url

@Repository
interface UrlRepository : MongoRepository<Url, Int> {
    fun findFirstById(id: Int) : Url?
}
