package aki.pvnghe.unittestapi.users.fragment

import aki.pvnghe.domain.model.User
import aki.pvnghe.domain.usecase.GetUsersListUseCase
import aki.pvnghe.unittestapi.baseview.BasePresenter
import javax.inject.Inject


class UsersPresenter @Inject constructor(private val getUsersListUseCase: GetUsersListUseCase) : BasePresenter<UsersView>() {

    private val SEARCH_KEY = "nghe"

    override fun initialise() {
        getView()?.initialiseView()
        getUsersListUseCase.execute(observer = UsersListObserver(this), params = SEARCH_KEY)
    }

    override fun disposeSubscriptions() {
        getUsersListUseCase.dispose()
    }

    fun showUserList(users: List<User>) {
        getView()?.showUserList(users)
    }

    fun showErrors(error: String) {
        getView()?.showError(error)
    }
}
