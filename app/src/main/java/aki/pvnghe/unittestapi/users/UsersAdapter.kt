package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.model.User
import aki.pvnghe.domain.model.avatarUri
import aki.pvnghe.unittestapi.R
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button

class UsersAdapter(private val context: Context, private val users: List<User>) :
        RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(val button: Button) : RecyclerView.ViewHolder(button)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val button = LayoutInflater.from(context)
                .inflate(R.layout.list_item_user, parent, false) as Button
        return ViewHolder(button)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.text = users[position].login
        holder.button.setOnClickListener {
            users[position].avatarUri()?.let {
                val intent = Intent(Intent.ACTION_VIEW, it)
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    override fun getItemCount() = users.size
}