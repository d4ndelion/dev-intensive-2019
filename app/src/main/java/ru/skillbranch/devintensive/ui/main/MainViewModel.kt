package ru.skillbranch.devintensive.ui.main


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.extensions.mutableLiveData

class MainViewModel: ViewModel() {
    private val chatRepository = ChatRepository
    private var chats = mutableLiveData(loadChats())

    fun getChatData() : LiveData<List<ChatItem>> {
        return chats
    }

    private fun loadChats(): List<ChatItem> {
        val chats = chatRepository.getChats()
        return chats.map {
            it.toChatItem()
        }.sortedBy {
            it.id.toInt()
        }
    }

    fun addItems() {
        val newItems = ChatRepository.getChats().map{ it.toChatItem() }
        val copy = chats.value!!.toMutableList()
        copy.addAll(newItems)
        chats.value = copy.sortedBy {
            it.id.toInt()
        }
    }

    fun addToArchive(chatId: String) {
        val chat = ChatRepository.find(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = true))
    }

    fun removeFromArchive(chatId: String) {
        val chat = ChatRepository.find(chatId)
        chat ?: return
        chatRepository.update(chat.copy(isArchived = false))
    }
}