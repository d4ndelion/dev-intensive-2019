package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.ui.main.ChatItem
import java.util.*

class Chat(
    val id: String,
    val title: String,
    val members: MutableList<User> = mutableListOf(),
    val messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived: Boolean = false
) {
    private fun lastMessageDate() = Date() // TODO implement these methods
    private fun lastMessageShort() = "Сообщений нет"
    private fun unreadMessageCount() = 0
    private fun isSingle() = members.size == 1
    fun toChatItem(): ChatItem {
            val user = members.first()
            return ChatItem(
                id,
                user.avatar,
                "??",
                "${user.firstName?:""} ${user.lastName?:""}",
                lastMessageShort(),
                unreadMessageCount(),
                lastMessageDate()?.shortFormat(),
                user.isOnline
            )

    }
}