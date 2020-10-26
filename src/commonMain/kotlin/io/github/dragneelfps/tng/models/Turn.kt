package io.github.dragneelfps.tng.models

import io.github.dragneelfps.tng.randomString
import kotlinx.serialization.Serializable

@Serializable
data class Turn(
    val id: String,
    val number: Int,
    val from_player: String,
    val to_player: String,
    var question: String? = null,
    var answer: String? = null,
) {

    companion object {
        fun create(number: Int, from_player: String, to_player: String) =
            Turn(id = randomString(), number, from_player, to_player)
    }
}