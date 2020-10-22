import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
import io.ktor.server.netty.*

fun Application.mainModule() {
  routing {
    game()
    static {
      resources("WEB-INF")
      defaultResource("WEB-INF/index.html")
    }
  }
}

//Only here for convenience, gradle/jar will work without it
fun main(args: Array<String>) = EngineMain.main(args)
