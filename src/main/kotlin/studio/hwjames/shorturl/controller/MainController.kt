package studio.hwjames.shorturl.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@RestController
class MainController {

    @GetMapping("/api/hello")
    fun main(): String {
        return "안녕하세요 현재 서버시간은 " + Date() + "입니다. \n";
    }
}