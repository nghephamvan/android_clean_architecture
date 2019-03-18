package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.model.User

interface UsersView {
    fun initialiseView()
    fun showUserList(users: List<User>)
}
