package aki.pvnghe.unittestapi.users

import aki.pvnghe.unittestapi.baseview.BaseActivity
import aki.pvnghe.unittestapi.R
import android.os.Bundle

class MainActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showUsersFragment()
    }

    private fun showUsersFragment() {
        addFragment(R.id.frame, UsersFragment().newInstance())
    }
}
