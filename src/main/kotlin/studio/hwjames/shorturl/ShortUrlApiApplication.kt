package studio.hwjames.shorturl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShortUrlApiApplication

fun main(args: Array<String>) {
    runApplication<ShortUrlApiApplication>(*args)
}
