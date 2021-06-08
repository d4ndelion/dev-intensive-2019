package ru.skillbranch.devintensive.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData

class MainViewModel: ViewModel() {
    fun getChatData() : LiveData<List<ChatItem>> {
        return mutableLiveData(loadChats())
    }

    private fun loadChats(): List<ChatItem> {
        val chats = chatRepository.loadChats()
        return chats.map {
            it.toChatItem()
        }
    }

    private val chatRepository = ChatRepository
}