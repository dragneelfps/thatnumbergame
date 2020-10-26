package io.github.dragneelfps

import io.github.dragneelfps.engine.GameEngine
import io.github.dragneelfps.engine.InMemoryGameEngine
import kotlin.test.Test

class GameplayTest {

    @Test
    fun `asd`() {
        val engine: GameEngine = InMemoryGameEngine()

        val game = engine.newGame()

        engine.addPlayer(game.id, "Sourabh")
        engine.addPlayer(game.id, "Rohit")

        engine.startGame(game.id)


        engine.ask(game.id, "q1")
        engine.answer(game.id, "123")
        engine.ask(game.id, "q2")
        engine.answer(game.id, "4345")
        val res = engine.completed(game.id)
        println(res)
    }

}