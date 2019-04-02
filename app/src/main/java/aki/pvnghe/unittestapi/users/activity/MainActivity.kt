package aki.pvnghe.unittestapi.users.activity

import aki.pvnghe.unittestapi.baseview.BaseActivity
import aki.pvnghe.unittestapi.R
import aki.pvnghe.unittestapi.users.fragment.UsersFragment
import android.os.Bundle

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.frame, UsersFragment().newInstance())
                    .commitAllowingStateLoss()
        }
    }

    override fun initialiseView() {

    }
}
