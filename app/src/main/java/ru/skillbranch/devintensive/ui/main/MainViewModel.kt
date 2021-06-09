package ru.skillbranch.devintensive.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData

class MainViewModel: ViewModel() {
    fun getChatData() : LiveData<List<ChatItem>> {
        return mutableLiveData(loadChats())
    }

    private fun loadChats(): List<ChatItem> {
        val chats = chatRepository.getChats()
        Log.d("asdasd", "${chats.size}")
        return chats.map {
            it.toChatItem()
        }
    }

    private val chatRepository = ChatRepository
}