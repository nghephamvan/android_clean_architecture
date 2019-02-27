package aki.pvnghe.unittestapi.main.presenter

import aki.pvnghe.unittestapi.main.view.SearchView
import aki.pvnghe.unittestapi.model.User
import aki.pvnghe.unittestapi.repository.UserRepository
import rx.Scheduler
import rx.Subscriber

open class MainPresenter(userRepository: UserRepository, ioScheduler: Scheduler, mainScheduler: Scheduler) : BasePresenter<SearchView>(), SearchPresenter {

    private var mainScheduler: Scheduler? = mainScheduler
    private var ioScheduler: Scheduler? = ioScheduler
    private var userRepository: UserRepository? = userRepository

    override fun onSearch(searchTerm: String) {
        checkViewAttached()
        getView()?.showLoading()

        userRepository?.searchUsers(searchTerm)?.subscribeOn(ioScheduler)?.observeOn(mainScheduler)?.subscribe(object : Subscriber<List<User>>() {
            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                getView()?.hideLoading()
                getView()?.showError(e.message)
            }

            override fun onNext(users: List<User>) {
                getView()?.hideLoading()
                getView()?.showSearchResults(users)
            }
        })?.let { addSubscription(it) }
    }

}