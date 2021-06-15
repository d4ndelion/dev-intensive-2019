package ru.skillbranch.devintensive.ui.group

data class UserItem (
        val id: String,
        val fullName: String,
        val initials: String?,
        val avatar: String?,
        val lastActivity: String,
        var isSelected: Boolean = false,
        var isOnline: Boolean = false
)