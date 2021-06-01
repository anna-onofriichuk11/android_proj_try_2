package com.example.tryengapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryengapp.R
import com.example.tryengapp.ui.adapters.model.User
import com.example.tryengapp.ui.extensions.load_image
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter(
    private var dbUsers: List<User>,
    private val dbContext: Context,
    private val open_chat_fragment: (userId: String) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(LayoutInflater.from(dbContext).inflate(R.layout.user_item, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(dbUsers[position], dbContext)
    }

    override fun getItemCount() = dbUsers.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val username: TextView = itemView.username
        private val profile_image: ImageView = itemView.avatar
        private val icon_online: CircleImageView = itemView.icon_online
        private val icon_offline: CircleImageView = itemView.icon_offline

        fun bind(user: User, context: Context) {
            username.text = user.username

            profile_image.loadImage(context, user.imageURL)
            icon_online.setVisibleOrGone(user.status == "online")
            icon_offline.setVisibleOrGone(user.status == "offline")

            itemView.setOnClickListener { openChatFragment.invoke(user.id) }
        }
    }
}