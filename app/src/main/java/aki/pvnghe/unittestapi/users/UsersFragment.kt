package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.model.User
import aki.pvnghe.mvp.BaseFragment
import aki.pvnghe.unittestapi.App
import aki.pvnghe.unittestapi.R
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

    fun newInstance(): UsersFragment {
        return UsersFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun initInjector() {
      DaggerUsersFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
                .inject(this)

    }

    override fun initialiseView() {
        user_list_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun showUserList(users: List<User>) {
        user_list_recycler_view.adapter = UsersAdapter(requireContext(), users)
    }

}