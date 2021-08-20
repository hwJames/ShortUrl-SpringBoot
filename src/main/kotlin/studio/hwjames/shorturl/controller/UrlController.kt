package studio.hwjames.shorturl.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import studio.hwjames.shorturl.repository.UrlRepository
import studio.hwjames.shorturl.util.Base62
import java.time.LocalDateTime
import javax.servlet.http.HttpServletResponse

@Controller
class UrlController {

    @Autowired
    private lateinit var urlRepository: UrlRepository

    @Autowired
    private lateinit var base62: Base62


    @GetMapping("/{shortUrl}")
    fun getRedirectUrl(@PathVariable shortUrl: String, response: HttpServletResponse): String {
        val url = urlRepository.findFirstById(base62.decoding(shortUrl))

        url?.let {
            val updateUrl = it
            updateUrl.readDate = LocalDateTime.now()
            updateUrl.readCnt = it.readCnt + 1

            urlRepository.save(updateUrl)

            response.sendRedirect(url.defaultUrl)
        }

        return "./index.html"
    }
}