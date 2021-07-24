package studio.hwjames.shorturl.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class MainController {

    @GetMapping("/")
    fun main(): String {
        return "Hello World!"
    }
}