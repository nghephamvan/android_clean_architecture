package aki.pvnghe.unittestapi.main.presenter

import aki.pvnghe.unittestapi.main.view.BaseView

interface Presenter<V: BaseView> {
    fun attachView(view: V)

    fun detachView()
}
