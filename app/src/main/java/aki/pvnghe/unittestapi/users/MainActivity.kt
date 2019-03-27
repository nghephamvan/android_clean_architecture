package aki.pvnghe.unittestapi.users

import aki.pvnghe.mvp.BaseActivity
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.R
import aki.pvnghe.unittestapi.users.di.component.DaggerUsersActivityComponent

class MainActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_main

    override fun initInjector() {
        DaggerUsersActivityComponent.builder()
                .appComponent((application as App).applicationComponent)
                .build()
                .inject(this)

        showUsersFragment()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(UsersFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }

        this.finish()
    }
    private fun showUsersFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .replace(R.id.frame, UsersFragment().newInstance(), UsersFragment.TAG)
            .commit()
    }
}
