import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.github.dragneelfps.models.Game
import io.github.dragneelfps.models.Question

fun Route.game() = route("/game/new") {
    get {
        call.respond("Hello gamer!!")
    }
//    post {
//        call.respond(
//            Game(
//                id = "1",
//
//                listOf(
//                    Question("Your name?"),
//                    Question("Your age?")
//                )
//            )
//        )
//    }
}
