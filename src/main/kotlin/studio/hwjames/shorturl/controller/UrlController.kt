package studio.hwjames.shorturl.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studio.hwjames.shorturl.model.Url
import studio.hwjames.shorturl.repository.UrlRepository
import studio.hwjames.shorturl.service.SequenceGeneratorService

@RestController
@RequestMapping("/api/v1/url")
class UrlController {

    @Autowired
    private lateinit var urlRepository: UrlRepository

    @Autowired
    private lateinit var sgs: SequenceGeneratorService

    @GetMapping("")
    fun getAll(): ResponseEntity<List<Url>> {
        val url = urlRepository.findAll()
        return ResponseEntity.ok(url)
    }

    @GetMapping("/{shortUrl}")
    fun getRedirectUrl(@PathVariable shortUrl: String): ResponseEntity<String> {
        val url = urlRepository.findFirstById(shortUrl.toInt())
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("없는 URL 입니다.")

        return ResponseEntity.ok(url.defaultUrl)
    }

    @PostMapping("")
    fun saveUrl(): ResponseEntity<String> {
        val url = Url(sgs.getSequenceNumber("urls_sequence"), "www.naver.com")
        urlRepository.save(url)

        return ResponseEntity.ok("success")
    }
}