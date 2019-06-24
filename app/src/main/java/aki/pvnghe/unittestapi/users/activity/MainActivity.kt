package aki.pvnghe.unittestapi.users.activity

import aki.pvnghe.domain.users.activity.MainPresenter
import aki.pvnghe.domain.users.activity.MainView
import aki.pvnghe.unittestapi.baseview.BaseActivity
import aki.pvnghe.unittestapi.R
import aki.pvnghe.unittestapi.users.fragment.UsersFragment
import android.os.Bundle

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun getLayout(): Int = R.layout.activity_main
    private var instanceState : Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        instanceState = savedInstanceState
        super.onCreate(savedInstanceState)
    }

    override fun initialiseView() {
        if (instanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame, UsersFragment().newInstance())
                .commitAllowingStateLoss()
        }
    }
}
