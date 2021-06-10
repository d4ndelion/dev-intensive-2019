package ru.skillbranch.devintensive.data.managers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.ui.main.ChatRepository

object CacheManager {
    val chats = mutableLiveData(ChatRepository.getChats())
    fun loadChats() : MutableLiveData<List<Chat>> {
       return chats
    }
}