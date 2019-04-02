package aki.pvnghe.unittestapi.users

import aki.pvnghe.unittestapi.baseview.BasePresenter

class MainPresenter: BasePresenter<MainView>() {

    override fun initialise() {
        getView()?.initialiseView()
    }

    override fun disposeSubscriptions() {}
}
