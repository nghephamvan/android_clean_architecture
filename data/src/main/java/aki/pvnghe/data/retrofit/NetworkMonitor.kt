package aki.pvnghe.data.retrofit

import android.content.Context
import android.net.ConnectivityManager

class NetworkMonitor(var appContext: Context) {
    fun isConnected(): Boolean {
        return (appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo?.isConnected
                ?: false
    }
}