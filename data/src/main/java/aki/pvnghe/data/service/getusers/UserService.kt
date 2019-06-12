package aki.pvnghe.data.service.getusers

import aki.pvnghe.data.model.User
import aki.pvnghe.data.service.BaseService
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Scheduler
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

interface UserApi {
    @GET("/search/users?per_page=10")
    fun searchGithubUsers(@Query("q") searchTerm: String?): Single<GetUsersResponse>
}

interface UserService {
    fun searchUsers(searchTerm: String?): Single<List<User>>
}

class UserServiceImpl @Inject constructor(private val mUserApi: UserApi) : UserService {
    override fun searchUsers(searchTerm: String?): Single<List<User>> = mUserApi.searchGithubUsers(searchTerm = searchTerm)
            .map {
                it.items
            }
}

class SearchUsersService @Inject constructor(
        private val mUserApi: UserApi,
        subscribeScheduler: Scheduler,
        postExecutionScheduler: Scheduler
) : BaseService<List<User>, String?>(subscribeScheduler, postExecutionScheduler) {
    override fun buildUseCaseSingle(params: String?): Single<List<User>> =
            mUserApi.searchGithubUsers(searchTerm = params).map { it.items }
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
