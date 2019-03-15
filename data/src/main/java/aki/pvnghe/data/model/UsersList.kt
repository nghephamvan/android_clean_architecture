package aki.pvnghe.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class UsersList(
    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null,
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null,
    @SerializedName("items")
    @Expose
    var items: ArrayList<User>? = null
)