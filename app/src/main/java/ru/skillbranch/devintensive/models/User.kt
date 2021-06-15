package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.ui.group.UserItem
import ru.skillbranch.devintensive.utils.parseFullName
import java.util.*

data class User(
           val id : String,
           var firstName : String?,
           var lastName : String?,
           var avatar : String?,
           var rating : Int = 0,
           var respect : Int = 0,
           var lastVisit : Date? = null,
           var isOnline : Boolean = false
) {
    constructor(userId: String,  firstName: String?, lastName: String?) : this(
        id = userId,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe $id")

    fun toUserItem(): UserItem {
        val lastActivity = when{
            lastVisit == null -> "Не заходил"
            isOnline -> "online"
            else -> "последний раз был $lastVisit"
        }
        return UserItem(
            id,
            "${firstName.orEmpty()} ${lastName.orEmpty()}",
            "??",
            avatar,
            lastActivity,
            false,
            isOnline
        )
    }

    fun printUser(){
        println("$firstName $lastName $id \n")
    }

    companion object Factory{
        private var lastId = -1
        fun makeUser(fullName: String?) : User{
            lastId++
            val (name, surname) = parseFullName(fullName)
            return User(userId = "$lastId", firstName = name, lastName = surname)
        }
    }
}