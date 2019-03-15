package aki.pvnghe.data.repository

import aki.pvnghe.data.model.User
import io.reactivex.Single

interface UserRepository {
    fun searchUsers(searchTerm: String): Single<List<User>>
}