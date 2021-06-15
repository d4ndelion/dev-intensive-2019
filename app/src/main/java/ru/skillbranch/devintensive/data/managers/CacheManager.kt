package ru.skillbranch.devintensive.data.managers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.devintensive.extensions.mutableLiveData
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.ui.group.UserAdapter
import ru.skillbranch.devintensive.ui.main.ChatRepository

object CacheManager {
    val chats = mutableLiveData(ChatRepository.getChats())
    val users: MutableList<User> = mutableListOf(
        User("123", "Denis", "Petrov", " ", 99, 99, null, true),
        User("123", "Denis", "Petrov", " ", 99, 99, null, true)
    )

    fun loadChats(): MutableLiveData<List<Chat>> {
        return chats
    }

    fun findUserByIds(ids: List<String>): MutableList<User> {
        return users.filter { ids.contains(it.id) } as MutableList<User>
    }

    fun nextChatId(): String {
        return "${chats.value!!.size}"
    }

    fun insertChat(chat:Chat) {
        val copy = chats.value!!.toMutableList()
        copy.add(chat)
        chats.value = copy
    }
}