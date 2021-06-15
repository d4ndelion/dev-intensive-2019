package ru.skillbranch.devintensive.ui.main

import ru.skillbranch.devintensive.models.ChatType

data class ChatItem(
    val id: String,
    val avatar: String?,
    val initials: String,
    val title: String,
    val shortDescription: String,
    val messageCount: Int = 0,
    val lastMessageDate: String?,
    val isOnline: Boolean = false,
    val chatType: ChatType = ChatType.SINGLE,
    var author: String? = null
)
