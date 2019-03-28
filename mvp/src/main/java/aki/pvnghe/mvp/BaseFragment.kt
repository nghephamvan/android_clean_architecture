package aki.pvnghe.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

abstract class BaseFragment<P: BasePresenter<Any>> : Fragment() {
    @Inject lateinit var presenter: P

    protected abstract fun getLayout(): Int
    protected abstract fun initInjector()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.inflate(getLayout(), container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

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