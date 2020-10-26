package io.github.dragneelfps.tng.engine

import io.github.dragneelfps.tng.models.*

fun Game.completed(): Boolean = status == GameStatus.FINISHED

fun Game.nextTurn(): Turn {
    if (completed()) throw IllegalArgumentException("Game is already finished")
    return when (type) {
        GameType.NORMAL -> {
            if (turns.isEmpty()) {
                Turn.create(1, players[0].id, players[1].id)
            } else {
                val lastTurn = turns.last()
                val lastPlayer = players.indexOfFirst { it.id == lastTurn.to_player }
                Turn.create(
                    turns.size + 1,
                    players[lastPlayer].id,
                    players[(lastPlayer + 1) % players.size].id
                )
            }
        }
        else -> throw NotImplementedError("Other game types not implemented")
//        GameType.RANDOM -> {
//            if (current_turn == null) {
//                val p1 = players.random()
//                randomTurnCreate(p1, 1)
//            } else {
//                val lastTurn = past_turns.last()
//                val p1 = players.first { it.id == lastTurn.to_player }
//                randomTurnCreate(p1, lastTurn.number + 1)
//            }
//        }
    }
}

private fun Game.randomTurnCreate(p1: Player, turnNum: Int): Turn {
    val toPlayer = (players - p1).random()
    return Turn.create(turnNum, p1.id, toPlayer.id)
}