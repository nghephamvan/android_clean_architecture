package aki.pvnghe.data.retrofit

import java.io.IOException

class NetworkException(var errorCode: ErrorCode) : IOException() {

    enum class ErrorCode(var code: Int) {
        NO_INTERNET_CONNECTION(10400),
        SSL_EXCEPTION(104001),
        SESSION_TIMEOUT(104002);
    }
}