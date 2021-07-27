package studio.hwjames.shorturl.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
class WebController {

    @Autowired
    var env: Environment? = null

    @GetMapping("/profile")
    fun getProfile(): String {
        return Arrays.stream(env!!.activeProfiles)
            .findFirst()
            .orElse("")
    }
}