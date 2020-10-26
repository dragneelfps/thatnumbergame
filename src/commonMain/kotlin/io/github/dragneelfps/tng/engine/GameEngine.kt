package io.github.dragneelfps.tng.engine

import io.github.dragneelfps.tng.models.Game

interface GameEngine {

    fun newGame(): String

    fun getGame(gameId: String): Game

    fun addQuestion(gameId: String, question: String): String

    fun addPlayer(gameId: String, name: String): String

    fun startGame(gameId: String)

    fun ask(gameId: String, questionId: String)

    fun answer(gameId: String, answer: String)

    fun markIfCompleted(gameId: String): Boolean

    fun completed(gameId: String): Boolean
}