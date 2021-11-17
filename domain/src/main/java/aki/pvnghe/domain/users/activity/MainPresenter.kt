package aki.pvnghe.domain.users.activity

import aki.pvnghe.domain.BasePresenter

class MainPresenter : BasePresenter<MainView>() {

    override fun initialise() {
        getView()?.initialiseView()
    }

    override fun disposeSubscriptions() {}
}

interface MainView {
    fun initialiseView()
}
