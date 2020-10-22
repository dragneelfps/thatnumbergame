import io.github.dragneelfps.models.Game
import io.github.dragneelfps.models.Question
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.game() {
    post("/game/new") {
        call.respond(
            Game(
                id = "1",
                listOf(
                    Question("Your name?"),
                    Question("Your age?")
                )
            )
        )
    }
}