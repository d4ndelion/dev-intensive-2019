package ru.skillbranch.devintensive.models

import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String = "$id ${from?.firstName} ${from?.lastName} $image ${if(isIncoming) "получил" else "отправил"} сообщение \"$image\" $date"
}