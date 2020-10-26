package io.github.dragneelfps.tng

class GameNotFound(val requestedGameId: String) : RuntimeException("Game[$requestedGameId] not found")