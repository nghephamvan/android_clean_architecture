package aki.pvnghe.domain.users.fragment

import aki.pvnghe.data.model.User
import aki.pvnghe.data.service.getusers.SearchUsersService
import aki.pvnghe.domain.BasePresenter
import javax.inject.Inject


class UsersPresenter @Inject constructor(private val searchUsersService: SearchUsersService) : BasePresenter<UsersView>() {

    override fun initialise() {
        searchUsersService.execute(observer = UsersListObserver(this), params = "nghe")
    }

    override fun disposeSubscriptions() {
        searchUsersService.dispose()
    }

    fun showUserList(users: List<User>) {
        getView()?.showUserList(users)
    }

    fun showErrors(error: String) {
        getView()?.showError(error)
    }
}
