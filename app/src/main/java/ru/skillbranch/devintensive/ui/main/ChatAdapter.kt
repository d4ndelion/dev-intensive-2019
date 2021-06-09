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
import ru.skillbranch.devintensive.R

class ChatAdapter(
    var items: List<ChatItem>,
    private val listener: (ChatItem) -> Unit
) : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {

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

    inner class SingleViewHolder(view: View) : RecyclerView.ViewHolder(view), ItemTouchViewHolder {
        val avatar: ImageView = view.findViewById(R.id.image_avatar)
        val title: TextView = view.findViewById(R.id.text_title)
        private val indicator: View = view.findViewById(R.id.online_indicator)
        private val date: TextView = view.findViewById(R.id.time_message)
        private val msgCount: TextView = view.findViewById(R.id.unread_count)
        private val messageBody: TextView = view.findViewById(R.id.text_message_item)


        fun bind(item: ChatItem, listener: (ChatItem) -> Unit) {
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
                Log.d("asdasd", "action")
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