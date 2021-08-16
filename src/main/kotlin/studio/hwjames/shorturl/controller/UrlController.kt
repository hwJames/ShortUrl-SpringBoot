package studio.hwjames.shorturl.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import studio.hwjames.shorturl.model.Url
import studio.hwjames.shorturl.repository.UrlRepository

@RestController
@RequestMapping("/api/v1/url")
class UrlController(
    private val urlRepository: UrlRepository
) {
    @GetMapping()
    fun getAll(): ResponseEntity<List<Url>> {
        val url = urlRepository.findAll()
        return ResponseEntity.ok(url)
    }

    @GetMapping("/{shortUrl}")
    fun getRedirectUrl(@PathVariable shortUrl: String): ResponseEntity<String> {
        val url = urlRepository.findFirstByShortUrl(shortUrl)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("없는 URL 입니다.")

        return ResponseEntity.ok(url.defaultUrl)
    }
}