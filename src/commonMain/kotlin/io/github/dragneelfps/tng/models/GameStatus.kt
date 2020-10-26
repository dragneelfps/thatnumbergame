package io.github.dragneelfps.tng.models

import kotlinx.serialization.Serializable

@Serializable
enum class GameStatus {
    INITIALIZED, ONGOING, FINISHED
}