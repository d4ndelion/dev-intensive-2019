package ru.skillbranch.devintensive.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.devintensive.R

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {
    var items : List<ChatItem> = listOf()

    inner class SingleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById<ImageView>(R.id.image_avatar)
        val title: TextView = view.findViewById<TextView>(R.id.text_title)
        val indicator: View = view.findViewById(R.id.online_indicator)
        val date: TextView = view.findViewById(R.id.time_message)
        val msgCount: TextView = view.findViewById(R.id.unread_count)
        val titleSingle: TextView = view.findViewById(R.id.text_message_item)


        fun bind(item: ChatItem) {
            indicator.visibility = if(item.isOnline) View.VISIBLE else View.GONE
            with(date){
                visibility = if (item.lastMessageDate!=null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }

            with(msgCount){
                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }

            title.text = item.shortDescription
            titleSingle.text = item.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatAdapter.SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.chat_item, parent, false)
        Log.d("MainActivityChatAdapter", "view created")
        return SingleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatAdapter.SingleViewHolder, position: Int) {
        Log.d("MainActivityChatAdapter", "view binded on pos $position")
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(data: List<ChatItem>) {
        items = data
        notifyDataSetChanged()
    }
}