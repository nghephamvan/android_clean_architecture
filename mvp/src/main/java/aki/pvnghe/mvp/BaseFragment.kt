package aki.pvnghe.mvp

import android.os.Bundle
import android.support.v4.app.DialogFragment
import javax.inject.Inject

abstract class BaseFragment<P: BasePresenter<Any>> : DialogFragment() {
    @Inject lateinit var presenter: P

    protected abstract fun initInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjector()
        initPresenter()
    }

    private fun initPresenter() {
        presenter.attachView(this)
        presenter.initialise()
    }

    override fun onDestroy() {
        presenter.disposeSubscriptions()
        presenter.detachView()
        super.onDestroy()
    }
}