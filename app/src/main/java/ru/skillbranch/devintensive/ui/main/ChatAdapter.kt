package ru.skillbranch.devintensive.ui.main

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.ChatType
import ru.skillbranch.devintensive.ui.custom.AvatarImageViewMask

class ChatAdapter(
    var items: List<ChatItem>,
    private val listener: (ChatItem) -> Unit
) : RecyclerView.Adapter<ChatAdapter.ChatItemViewHolder>() {

    companion object{
        private const val ARCHIVE_TYPE = 0
        private const val SINGLE_TYPE = 1
        private const val GROUP_TYPE = 2
    }

    override fun getItemViewType(position: Int): Int = when(items[position].chatType){
        ChatType.ARCHIVE ->ARCHIVE_TYPE
        ChatType.SINGLE ->SINGLE_TYPE
        ChatType.GROUP ->GROUP_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatAdapter.ChatItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            SINGLE_TYPE -> SingleViewHolder(inflater.inflate(R.layout.chat_item, parent, false))
            GROUP_TYPE -> GroupViewHolder(inflater.inflate(R.layout.group_chat_item, parent, false))
            else -> SingleViewHolder(inflater.inflate(R.layout.chat_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ChatAdapter.ChatItemViewHolder, position: Int) {
        Log.d("asdasd", "view binded on pos $position")
        items[position].let { it ->
            holder.bind(it) { listener(it) }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(data: List<ChatItem>) {
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

    abstract inner class ChatItemViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        abstract fun bind(item: ChatItem, listener: (ChatItem) -> Unit)
    }

    inner class SingleViewHolder(convertView: View) : ChatItemViewHolder(convertView), ItemTouchViewHolder {
        val avatar: ImageView = convertView.findViewById(R.id.image_avatar)
        val title: TextView = convertView.findViewById(R.id.text_title)
        private val indicator: View = convertView.findViewById(R.id.online_indicator)
        private val date: TextView = convertView.findViewById(R.id.time_message)
        private val msgCount: TextView = convertView.findViewById(R.id.unread_count)
        private val messageBody: TextView = convertView.findViewById(R.id.text_message_item)

        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            Glide.with(itemView)
                .load(item.avatar)
                .into(avatar)
            indicator.visibility = if(item.isOnline) View.VISIBLE else View.GONE
            with(date){
                visibility = if (item.lastMessageDate!=null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }

            with(msgCount){
                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }

            title.text = item.title
            messageBody.text = item.shortDescription
            itemView.setOnClickListener {
                Log.d("SingleViewHolder", "action")
                listener.invoke(item)
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }
    }

    inner class GroupViewHolder(convertView: View) : ChatItemViewHolder(convertView), ItemTouchViewHolder {
        val avatar: AvatarImageViewMask = convertView.findViewById(R.id.image_avatar)
        val title: TextView = convertView.findViewById(R.id.text_title)
        private val date: TextView = convertView.findViewById(R.id.time_message)
        private val msgCount: TextView = convertView.findViewById(R.id.unread_count)
        private val messageBody: TextView = convertView.findViewById(R.id.text_message_item)
        private val author: TextView = convertView.findViewById(R.id.text_message_author)

        override fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
            with(date){
                visibility = if (item.lastMessageDate!=null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }

            with(msgCount){
                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }

            title.text = item.title
            messageBody.text = item.shortDescription
            with(author){
                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.author
            }
            itemView.setOnClickListener {
                Log.d("GroupViewHolder", "action")
                listener.invoke(item)
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemCleared() {
            itemView.setBackgroundColor(Color.WHITE)
        }
    }
}