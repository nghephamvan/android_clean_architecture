package aki.pvnghe.unittestapi.baseview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseFragment<P: BasePresenter<Any>> : Fragment(), HasSupportFragmentInjector {
    @Inject lateinit var presenter: P
    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    protected abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflater.inflate(getLayout(), container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector
}