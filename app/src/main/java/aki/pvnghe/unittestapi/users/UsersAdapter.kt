package aki.pvnghe.unittestapi.users

import aki.pvnghe.domain.model.User
import aki.pvnghe.domain.model.avatarUri
import aki.pvnghe.unittestapi.R
import aki.pvnghe.unittestapi.di.module.GlideApp
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_user.view.*
import com.bumptech.glide.load.resource.bitmap.CircleCrop

class UsersAdapter(private val context: Context, private val users: List<User>) :
        RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image = view.listview_image
        val button = view.btn_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        GlideApp.with(context)
            .load(users.get(position).avatarUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.ic_launcher_background)
            .transform(CircleCrop())
            .into(holder.image)

        holder.button.text = users[position].login

        holder.view.setOnClickListener {
            users[position].avatarUri()?.let {
                val intent = Intent(Intent.ACTION_VIEW, it)
                ContextCompat.startActivity(context, intent, null)
            }
        }

        holder.button.setOnClickListener {
            users[position].avatarUri()?.let {
                val intent = Intent(Intent.ACTION_VIEW, it)
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    override fun getItemCount() = users.size
}