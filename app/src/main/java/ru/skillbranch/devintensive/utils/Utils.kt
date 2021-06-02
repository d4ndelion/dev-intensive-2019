package ru.skillbranch.devintensive.utils

    fun parseFullName(fullname: String?): Pair<String?, String?>{
        val parts : List<String>? = fullname?.split(" ")
        val firstname = parts?.getOrNull(0)
        val lastname = parts?.getOrNull(1)
        return Pair(firstname, lastname)
    }
