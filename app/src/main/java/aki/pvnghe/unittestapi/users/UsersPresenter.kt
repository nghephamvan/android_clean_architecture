package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.domain.model.User
import aki.pvnghe.mvp.BasePresenter
import javax.inject.Inject


class UsersPresenter @Inject constructor(private val getUsersListUseCase: GetUsersListUseCase) : BasePresenter<UsersView>() {

    override fun initialise() {
        getView()?.initialiseView()
        getUsersListUseCase.execute(UsersListObserver(this))
    }

    override fun disposeSubscriptions() {
        getUsersListUseCase.dispose()
    }

    fun showUserList(users: List<User>) {
        getView()?.showUserList(users)
    }
}
