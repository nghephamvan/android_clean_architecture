package aki.pvnghe.data.service.getusers

import aki.pvnghe.data.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

interface UserApi {
    @GET("/search/users?per_page=10")
    fun searchGithubUsers(@Query("q") searchTerm: String?): Single<GetUsersResponse>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String?): Single<User>
}

interface UserService {
    fun searchUsers(searchTerm: String?): Single<List<User>>
    fun getUser(name: String): Single<User>
}

class UserServiceImpl @Inject constructor(private val mUserApi: UserApi) : UserService {
    override fun searchUsers(searchTerm: String?): Single<List<User>> = mUserApi.searchGithubUsers(searchTerm = searchTerm)
            .map {
                it.items
            }

    override fun getUser(name: String): Single<User> = mUserApi.getUser(username = name)/*Single.create { emitter -> run { emitter.onSuccess(User("nghephamvan")) } }*/
}

data class GetUsersResponse(
        @SerializedName("total_count")
        @Expose
        var totalCount: Int? = null,
        @SerializedName("incomplete_results")
        @Expose
        var incompleteResults: Boolean? = null,
        @SerializedName("items")
        @Expose
        var items: List<User>? = null
)

/*
//we also can define api Request if api has more one param. Ex:
data class GetUsersRequest(
        @SerializedName("searchText")
        val searchText: String,
        @SerializedName("minAge")
        val minAge: Int,
        @SerializedName("maxAge")
        val maxAge: Int
)
*/
