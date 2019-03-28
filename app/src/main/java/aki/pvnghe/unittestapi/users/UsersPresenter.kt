package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.GetUsersListUseCase
import aki.pvnghe.domain.model.User
import aki.pvnghe.mvp.BasePresenter
import javax.inject.Inject


class UsersPresenter @Inject constructor(private val getUsersListUseCase: GetUsersListUseCase) : BasePresenter<UsersView>() {

    private val SEARCH_KEY = "nghe"

    override fun initialise() {
        getUsersListUseCase.execute(UsersListObserver(this), searchTerm = SEARCH_KEY)
    }

    override fun disposeSubscriptions() {
        getUsersListUseCase.dispose()
    }

    fun showUserList(users: List<User>) {
        getView()?.initialiseView()
        getView()?.showUserList(users)
    }
}
