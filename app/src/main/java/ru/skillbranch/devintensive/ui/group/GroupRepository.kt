package ru.skillbranch.devintensive.ui.group

import ru.skillbranch.devintensive.data.managers.CacheManager
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.ui.main.ChatRepository

object GroupRepository {
    fun loadUsers(): List<User> = listOf(
        User(
            id = "13123"
        ),
        User(
            id = "131",
            "Simon",
            "Bolivar",
            null
        )
    )

    fun createChat(items: List<UserItem>) {
        val ids = items.map { it.id }
        val users = CacheManager.findUserByIds(ids)
        val title = users.map { it.firstName }.joinToString(", ")
        val chat = Chat(CacheManager.nextChatId(), title, users)
        CacheManager.insertChat(chat)
    }

}