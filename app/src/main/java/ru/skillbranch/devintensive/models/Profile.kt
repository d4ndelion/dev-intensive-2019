package ru.skillbranch.devintensive.models

data class Profile(
    val rank: String = "Rookie",
    val firstName: String = "John",
    val secondName: String = "Doe",
    val description: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
){
    private val nickname = "$firstName + $secondName"
    fun toMap(): Map<String, Any> = mapOf(
        "rank" to rank,
        "firstName" to firstName,
        "secondName" to secondName,
        "description" to description,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect,
        "nickname" to nickname
    )
}
