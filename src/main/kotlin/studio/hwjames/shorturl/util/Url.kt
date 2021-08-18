package studio.hwjames.shorturl.util

import org.springframework.stereotype.Component
import java.net.URL

@Component
class Url {
    fun check(_url: String): Boolean {
        try {
            val connectionUrl = URL(_url)
            val conn = connectionUrl.openConnection()
            conn.connect()
        } catch (error: Exception) {
            return false
        }
        return true
    }
}