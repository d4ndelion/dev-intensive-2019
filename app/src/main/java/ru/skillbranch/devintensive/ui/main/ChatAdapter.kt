package ru.skillbranch.devintensive.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ru.skillbranch.devintensive.R

class ChatAdapter(private var items : LiveData<List<ChatItem>>) : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatAdapter.SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.chat_item, parent, false)
        Log.d("asdasd", "view created")
        return SingleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatAdapter.SingleViewHolder, position: Int) {
        Log.d("asdasd", "view binded on pos $position")
        items.value?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = items.value?.size ?: 0

    inner class SingleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.image_avatar)
        val title: TextView = view.findViewById(R.id.text_title)
        private val indicator: View = view.findViewById(R.id.online_indicator)
        private val date: TextView = view.findViewById(R.id.time_message)
        private val msgCount: TextView = view.findViewById(R.id.unread_count)
        private val messageBody: TextView = view.findViewById(R.id.text_message_item)


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

            title.text = item.title
            messageBody.text = item.shortDescription
        }
    }
}