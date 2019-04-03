package aki.pvnghe.unittestapi.users.activity

import aki.pvnghe.unittestapi.baseview.BasePresenter

class MainPresenter: BasePresenter<MainView>() {

    override fun initialise() {
        getView()?.initialiseView()
    }

    override fun disposeSubscriptions() {}
}
