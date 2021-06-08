package ru.skillbranch.devintensive.ui.main

data class ChatItem(
    val id: String,
    val avatar: String?,
    val initials: String,
    val title: String,
    val shortDescription: String,
    val messageCount: Int = 0,
    val lastMessageDate: String?,
    val isOnline: Boolean = false
)
