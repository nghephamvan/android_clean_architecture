package aki.pvnghe.data.retrofit

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.security.GeneralSecurityException
import javax.net.ssl.SSLPeerUnverifiedException

class CommonInterceptor(var networkMonitor: NetworkMonitor) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (networkMonitor.isConnected()) {
            try {
                return chain.proceed(processRequest(chain.request()))//decryptResponse(response)
            } catch (slle: SSLPeerUnverifiedException) {
                throw NetworkException(NetworkException.ErrorCode.SSL_EXCEPTION)
            } catch (e: Exception) {
                throw e
            }
        } else {
            throw NetworkException(NetworkException.ErrorCode.NO_INTERNET_CONNECTION)
        }
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    fun processRequest(request: Request): Request {
        return request.newBuilder()
                .apply {
                    header("Content-Type", "application/json")
                    method(request.method(), request.body()/*encryptRequestBody(requestBody)*/)
                }.build()
    }

    /*//Example decrypt
    fun decryptResponse(response: Response): Response {
        val responseBody = response.body()
        val decryptText: String = "decryptText"//Crypto.getInstance().decryptWithAES(responseBody.string())
        val body = ResponseBody.create(responseBody?.contentType(), decryptText)
        return response.newBuilder().body(body).build()
    }

    //Example encrypt requestBody
    fun encryptRequestBody(requestBody: RequestBody): RequestBody {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        var charset = Charsets.UTF_8
        requestBody.contentType()?.let { it.charset(Charsets.UTF_8)?.let { charset = it } }
        val bodyJson = Gson().fromJson(buffer.readString(charset), JsonObject::class.java)

        val requestBodyJson = bodyJson.getAsJsonObject("requestBody")
        var clientDataJson = requestBodyJson.getAsJsonObject("clientData")
        val encryptClientData: String = "encryptClientData"//Crypto.GetInstance().encryptWithAES(clientDataJson.toString())
        requestBodyJson.addProperty("clientData", encryptClientData)

        bodyJson.add("requestBody", requestBodyJson)

        val requestBodyWithEncrypt = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestBodyJson.toString())
        return requestBodyWithEncrypt
    }*/
}