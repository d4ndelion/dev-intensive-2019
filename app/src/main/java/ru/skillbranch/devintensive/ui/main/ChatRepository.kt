package ru.skillbranch.devintensive.ui.main

import androidx.lifecycle.MutableLiveData
import ru.skillbranch.devintensive.data.managers.CacheManager
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User

object ChatRepository {
    fun getChats(): List<Chat> {
        return listOf(
            Chat(
                "751",
                "Some title",
                mutableListOf(User("231"))
            ),
            Chat(
                "623462",
                "some title 1",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "45674",
                "some title 2",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "85",
                "some title 3",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "246",
                "some title 4",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "27457",
                "some title 5",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "4756",
                "some title 6",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "245627",
                "some title 7",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "8576",
                "some title 8",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "12321",
                "some title 9",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            ),
            Chat(
                "432161",
                "some title 10",
                mutableListOf(User("123", "Denis", "Petrov", " ", 99, 99, null, true), User("14213"))
            )
        )
    }

    private val chats = CacheManager.loadChats()

    fun loadChats() : MutableLiveData<List<Chat>> {
        return chats
    }

    fun update(chat: Chat) {
        val copy = chats.value!!.toMutableList()
        val ind = chats.value!!.indexOfFirst { it.id == chat.id }
        if (ind == -1) return
        copy[ind] = chat
        chats.value = copy
    }

    fun find(chatId: String): Chat? {
        val ind = chats.value!!.indexOfFirst {
            it.id == chatId
        }
        return chats.value!!.getOrNull(ind)
    }
}