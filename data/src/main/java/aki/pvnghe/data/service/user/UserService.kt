package aki.pvnghe.data.service.user

import aki.pvnghe.data.model.User
import io.reactivex.Single

interface UserService {
    fun searchUsers(searchTerm: String?): Single<List<User>>
    fun getUser(name: String): Single<User>
}