package aki.pvnghe.data.service.user

import aki.pvnghe.data.model.User
import aki.pvnghe.data.model.UsersList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("/search/users?per_page=10")
    fun searchGithubUsers(@Query("q") searchTerm: String?): Single<UsersList>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String?): Single<User>
}