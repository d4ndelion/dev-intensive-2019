package ru.skillbranch.devintensive.models

data class Profile(
    val firstName: String = "John",
    val secondName: String = "Doe",
    val description: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
){
    private val rank: String = "Rookie"
    private val nickname = "John Doe"
    fun toMap(): Map<String, Any> = mapOf(
        "firstName" to firstName,
        "secondName" to secondName,
        "rank" to rank,
        "description" to description,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect,
        "nickname" to nickname
    )
}
