package aki.pvnghe.data.service.user

import aki.pvnghe.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class UserServiceImpl @Inject constructor(private val mUserApi: UserApi) : UserService {
    override fun searchUsers(searchTerm: String?): Single<List<User>> = mUserApi.searchGithubUsers(searchTerm = searchTerm)
            .map {
                it.items
            }

    override fun getUser(name: String): Single<User> = mUserApi.getUser(username = name)/*Single.create { emitter -> run { emitter.onSuccess(User("nghephamvan")) } }*/
}