package io.github.dragneelfps.tng.models

data class Game(
    val id: String,
    val status: GameStatus,
    val type: GameType,
    val questions: List<Question>,
    val players: List<Player>,
    val current_turn_id: String,
    val turns: List<Turn>,
)