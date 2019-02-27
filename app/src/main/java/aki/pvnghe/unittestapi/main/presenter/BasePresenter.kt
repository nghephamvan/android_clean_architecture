package aki.pvnghe.unittestapi.main.presenter

import aki.pvnghe.unittestapi.main.view.BaseView
import rx.Subscription
import rx.subscriptions.CompositeSubscription
import java.lang.RuntimeException

open class BasePresenter<V : BaseView> : Presenter<V> {

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        compositeSubscription.clear()
        view = null
    }
    private var view: V? = null
    private val compositeSubscription = CompositeSubscription()

    fun getView(): V? {
        return view
    }

    private fun isViewAttached(): Boolean {
        return view != null
    }

    protected fun addSubscription(subscription: Subscription) {
        this.compositeSubscription.add(subscription)
    }

    fun checkViewAttached() {
        if (!isViewAttached()) {
            throw RuntimeException("Null view in presenter")
        }
    }
}