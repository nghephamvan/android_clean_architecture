package aki.pvnghe.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.net.Uri

data class User(
    @SerializedName("login")
    @Expose
    var login: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null,
    @SerializedName("bio")
    @Expose
    var bio: String? = null)

fun User.avatarUri(): Uri? = Uri.parse(avatarUrl)