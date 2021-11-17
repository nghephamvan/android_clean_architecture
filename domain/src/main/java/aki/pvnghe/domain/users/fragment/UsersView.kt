package aki.pvnghe.domain.users.fragment

import aki.pvnghe.data.model.User

interface UsersView {
    fun showUserList(users: List<User>)
    fun showProgress(show: Boolean)
    fun showError(errorMessage: String)
    fun initialiseView()
}
