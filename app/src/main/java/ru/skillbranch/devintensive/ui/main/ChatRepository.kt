package ru.skillbranch.devintensive.ui.main

import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User

object ChatRepository {
    fun getChats(): List<Chat> {
        return listOf(
            Chat(
                "asd",
                "Some title",
                mutableListOf(User("231"))
            ),
            Chat(
                "2231",
                "some title 2",
                mutableListOf(User("3321"), User("14213"))
            )
        )
    }
}