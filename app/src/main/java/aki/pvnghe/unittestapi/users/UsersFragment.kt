package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.model.User
import aki.pvnghe.unittestapi.baseview.BaseFragment
import aki.pvnghe.unittestapi.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjector
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : BaseFragment<UsersPresenter>(), UsersView {
    companion object {
        val TAG: String = "UsersFragment"
    }

    override fun getLayout(): Int = R.layout.fragment_users

    fun newInstance(): UsersFragment {
        return UsersFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_users, container, false)
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
            adapter = UsersAdapter(activity!!, users)
        }

        showProgress(false)
    }
}