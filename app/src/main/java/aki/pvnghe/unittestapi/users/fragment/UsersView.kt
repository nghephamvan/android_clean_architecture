package aki.pvnghe.unittestapi.users.fragment

import aki.pvnghe.domain.model.User

interface UsersView {
    fun initialiseView()
    fun showUserList(users: List<User>)
    fun showProgress(show: Boolean)
    fun showError(errorMessage: String)
}
