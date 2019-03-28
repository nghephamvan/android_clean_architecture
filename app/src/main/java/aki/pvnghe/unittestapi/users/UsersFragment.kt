package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.model.User
import aki.pvnghe.mvp.BaseFragment
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.R
import aki.pvnghe.unittestapi.users.di.component.DaggerUsersFragmentComponent
import aki.pvnghe.unittestapi.users.di.module.FragmentModule
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : BaseFragment<UsersPresenter>(), UsersView {

    companion object {
        val TAG: String = "UsersFragment"
    }

    override fun getLayout(): Int = R.layout.fragment_users

    fun newInstance(): UsersFragment {
        return UsersFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun initInjector() {
      DaggerUsersFragmentComponent.builder()
                .appComponent((activity?.application as App).applicationComponent)
                .fragmentModule(FragmentModule())
                .build()
                .inject(this)

    }

    override fun initialiseView() {
        showProgress(true)
        user_list_recycler_view?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showUserList(users: List<User>) {
        user_list_recycler_view?.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(activity)
            adapter = UsersAdapter(activity!!, users)
        }

        showProgress(false)
    }

}