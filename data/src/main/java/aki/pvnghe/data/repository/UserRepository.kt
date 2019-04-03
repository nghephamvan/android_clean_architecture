package aki.pvnghe.data.repository

import aki.pvnghe.data.model.User

interface UserRepository {
    fun deleteAllUsers(callback: () -> Unit)
    fun insertUsersList(usersList: List<User>?, callback: () -> Unit)
}