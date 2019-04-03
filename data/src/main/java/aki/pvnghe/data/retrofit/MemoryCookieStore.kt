package aki.pvnghe.data.retrofit

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Primitive cookie store that stores cookies in a (volatile) hash map.
 * Will be sufficient for session cookies.
 */
class MemoryCookieStore : CookieJar {
    private val cookieStore = ConcurrentHashMap<String, MutableList<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        cookieStore[url.uri().host] = cookies
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> = cookieStore[url.uri().host]
            ?: Collections.emptyList<Cookie>()
}