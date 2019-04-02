package aki.pvnghe.unittestapi.users.fragment

import aki.pvnghe.domain.model.User
import io.reactivex.observers.DisposableSingleObserver

class UsersListObserver(private val presenter: UsersPresenter) : DisposableSingleObserver<List<User>>() {
    override fun onSuccess(users: List<User>) {
        presenter.showUserList(users)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        val error = e.message ?: "Error$e"
        presenter.showErrors(error)
    }
}
