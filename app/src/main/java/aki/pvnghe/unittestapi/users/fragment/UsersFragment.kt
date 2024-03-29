package aki.pvnghe.unittestapi.users.fragment

import aki.pvnghe.data.model.User
import aki.pvnghe.domain.users.fragment.UsersPresenter
import aki.pvnghe.domain.users.fragment.UsersView
import aki.pvnghe.unittestapi.R
import aki.pvnghe.unittestapi.baseview.BaseFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : BaseFragment<UsersPresenter>(), UsersView {

    override fun getLayout(): Int = R.layout.fragment_users

    fun newInstance() = UsersFragment()

    override fun initialiseView() {
        showProgress(true)
        user_list_recycler_view?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun showUserList(users: List<User>) {
        user_list_recycler_view?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = UsersAdapter(activity!!, users)
        }

        showProgress(false)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progress_bar?.visibility = View.VISIBLE
        } else {
            progress_bar?.visibility = View.GONE
        }
    }

    override fun showError(errorMessage: String) {
        showProgress(false)
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}