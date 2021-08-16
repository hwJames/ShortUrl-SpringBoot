package studio.hwjames.shorturl.util

import org.springframework.stereotype.Component

@Component
class Base62 {
    val base62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray()

    fun encoding(_value: Int): String {
        var value = _value
        val sb = StringBuilder()
        do {
            val i = value % 62
            sb.append(base62[i])
            value /= 62
        } while (value > 0)
        return sb.toString()
    }

    fun decoding(_value: String): Int {
        var result = 0
        var power = 1
        for (element in _value) {
            val digit = String(base62).indexOf(element)
            result += digit * power
            power *= 62
        }
        return result
    }
}