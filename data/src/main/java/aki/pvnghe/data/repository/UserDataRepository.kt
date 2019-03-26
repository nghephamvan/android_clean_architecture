package aki.pvnghe.data.repository

import aki.pvnghe.data.model.User
import aki.pvnghe.data.service.GithubUserService
import io.reactivex.Single
import javax.inject.Inject

class UserDataRepository @Inject constructor(private var githubUserService: GithubUserService) : UserRepository {
    override fun searchUsers(searchTerm: String?): Single<List<User>> = githubUserService.searchGithubUsers(searchTerm = searchTerm)
            .map { it.items }

    override fun getUser(name: String): Single<User> = /*Single.create { emitter -> run { emitter.onSuccess(User("nghephamvan")) } }*/githubUserService.getUser(username = name)

}