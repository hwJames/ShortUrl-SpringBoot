package studio.hwjames.shorturl.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import studio.hwjames.shorturl.model.Url
import studio.hwjames.shorturl.repository.UrlRepository
import studio.hwjames.shorturl.service.SequenceGeneratorService
import studio.hwjames.shorturl.util.Base62
import studio.hwjames.shorturl.util.UrlUtil
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/url")
class UrlController {

    @Autowired
    private lateinit var urlRepository: UrlRepository

    @Autowired
    private lateinit var sgs: SequenceGeneratorService

    @Autowired
    private lateinit var base62: Base62

    @Autowired
    private lateinit var urlUtil: UrlUtil

    @GetMapping("/{shortUrl}")
    fun getRedirectUrl(@PathVariable shortUrl: String): HashMap<String, Any> {
        val result = HashMap<String, Any>()

        val url = urlRepository.findFirstById(base62.decoding(shortUrl))

        url?.let {
            val updateUrl = it
            updateUrl.readDate = LocalDateTime.now()
            updateUrl.readCnt = it.readCnt + 1

            urlRepository.save(updateUrl)

            result["statusCode"] = 200
            result["message"] = "성공"
            result["data"] = url.defaultUrl
        } ?: run {
            result["statusCode"] = 204
            result["message"] = "찾을 수 없는 URL 입니다."
        }

        return result
    }

    @PostMapping("")
    fun saveUrl(@RequestBody payload: HashMap<String, Any>): HashMap<String, Any> {
        val baseUrl = payload["url"] as String
        val result = HashMap<String, Any>()

        if(urlUtil.check(baseUrl)) {
            val url = urlRepository.save(Url(sgs.getSequenceNumber("urls_sequence"), baseUrl))

            result["statusCode"] = 200
            result["message"] = "성공"
            result["data"] = HashMap<String, Any>().apply {
                this["shortUrl"] = base62.encoding(url.id)
                this["defaultUrl"] = url.defaultUrl
            }

            return result
        } else {
            result["statusCode"] = 400
            result["message"] = "유효하지 않는 URL 입니다."

            return result
        }
    }
}