package aki.pvnghe.unittestapi.baseview

import aki.pvnghe.domain.BasePresenter
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<P : BasePresenter<Any>> : DaggerAppCompatActivity() {

    @Inject lateinit var presenter: P

    protected abstract fun getLayout(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initPresenter()
    }

    private fun initPresenter() {
        presenter.attachView(this)
        presenter.initialise()
        initialiseView()
    }

    override fun onDestroy() {
        presenter.disposeSubscriptions()
        presenter.detachView()
        super.onDestroy()
    }

    abstract fun initialiseView()
}
