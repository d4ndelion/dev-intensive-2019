package ru.skillbranch.devintensive.ui.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import ru.skillbranch.devintensive.R

class UserAdapter(val listener: (UserItem) -> Unit) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var items: List<UserItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_user_list, parent, false)
        return UserViewHolder(convertView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(items[position], listener)

    override fun getItemCount() = items.size

    fun updateData(data: List<UserItem>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = items.size ?: 0

            override fun getNewListSize(): Int = data.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = data[newItemPosition] == items[oldItemPosition]

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = data[newItemPosition].hashCode() == items[oldItemPosition].hashCode()
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = data
        notifyDataSetChanged()
    }

    inner class UserViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        private val avatar: ImageView = convertView.findViewById(R.id.iv_avatar_user)
        private val indicator: View = convertView.findViewById(R.id.online_indicator)
        private val userName: TextView = convertView.findViewById(R.id.tv_user_name)
        private val lastActivity: TextView = convertView.findViewById(R.id.tv_last_activity)
        private val selected: ImageView = convertView.findViewById(R.id.iv_selected)


        override val containerView: View?
            get() = itemView

        fun bind(user: UserItem, listener: (UserItem) -> Unit) {
            Glide.with(itemView)
                .load(user.avatar)
                .into(avatar)

            indicator.visibility = if (user.isOnline) View.INVISIBLE else View.GONE
            userName.text = user.fullName
            lastActivity.text = user.lastActivity
            selected.visibility = if(user.isSelected) View.INVISIBLE else View.GONE
            itemView.setOnClickListener {
                listener.invoke(user)
            }
        }
    }
}