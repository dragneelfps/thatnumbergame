package io.github.dragneelfps.tng.engine

import io.github.dragneelfps.tng.find
import io.github.dragneelfps.tng.models.*
import io.github.dragneelfps.tng.randomString

class InMemoryGameEngine : GameEngine {

    override fun newGame(): String {
        val game = Game(
            id = randomString(),
            status = GameStatus.INITIALIZED,
            type = GameType.NORMAL,
            questions = emptyList(),
            players = emptyList(),
            current_turn_id = "",
            turns = emptyList()
        )
        games[game.id] = game
        return game.id
    }

    override fun getGame(gameId: String): Game = games.find(gameId)

    override fun addQuestion(gameId: String, question: String): String {
        games.find(gameId).let { game ->
            val q = Question(id = randomString(), value = question)
            games[gameId] = game.copy(questions = game.questions + q)
            return q.id
        }
    }

    override fun addPlayer(gameId: String, name: String): String {
        games.find(gameId).let { game ->
            val player = Player(id = randomString(), name = name)
            games[gameId] = game.copy(players = game.players + player)
            return player.id
        }
    }

    override fun startGame(gameId: String) {
        games.find(gameId).let { game ->
            val turn = game.nextTurn()
            games[gameId] = game.copy(status = GameStatus.ONGOING, current_turn_id = turn.id, turns = game.turns + turn)
        }
    }

    override fun ask(gameId: String, questionId: String) {
        games.find(gameId).let { game ->
            val turn = game.turns.first { it.id == game.current_turn_id }
            turn.question = questionId
        }
    }

    override fun answer(gameId: String, answer: String) {
        games.find(gameId).let { game ->
            val turn = game.turns.first { it.id == game.current_turn_id }
            turn.answer = answer
            if (!markIfCompleted(gameId)) {
                val nextTurn = game.nextTurn()
                games[gameId] = game.copy(current_turn_id = nextTurn.id, turns = game.turns + nextTurn)
            }
        }
    }

    override fun markIfCompleted(gameId: String): Boolean {
        games.find(gameId).let { game ->
            if (game.turns.all { it.answer != null } && game.turns.size == game.questions.size) {
                games[gameId] = game.copy(status = GameStatus.FINISHED)
                return true
            }
        }
        return false
    }

    override fun completed(gameId: String): Boolean {
        games.find(gameId).let { game -> return game.completed() }
    }

    companion object {
        private val games = mutableMapOf<String, Game>()
    }
}