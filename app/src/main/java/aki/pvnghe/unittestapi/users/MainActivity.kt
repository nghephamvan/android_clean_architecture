package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.model.User
import aki.pvnghe.mvp.BaseActivity
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.R
import aki.pvnghe.unittestapi.users.di.component.DaggerUsersComponent
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<UsersPresenter>(), UsersView {
    override fun getLayout(): Int = R.layout.activity_main

    override fun initInjector() {
        DaggerUsersComponent.builder()
                .appComponent((application as App).applicationComponent)
                .build()
                .inject(this)
    }

    override fun initialiseView() {
        user_list_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun showUserList(users: List<User>) {
        user_list_recycler_view.adapter = UsersAdapter(this, users)
    }
}
