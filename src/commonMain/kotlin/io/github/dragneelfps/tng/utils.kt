package io.github.dragneelfps.tng

import io.github.dragneelfps.tng.models.Game
import kotlin.random.Random

fun randomString(): String {
    return generateSequence { Random.nextInt(CHAR_POOL.size) }
        .take(CHAR_POOL.size)
        .map { CHAR_POOL[it] }
        .joinToString(separator = "")
}

private val CHAR_POOL = ('a'..'z') + ('A'..'Z') + ('0'..'9')


fun Map<String, Game>.find(gameId: String) = this[gameId] ?: throw GameNotFound(gameId)