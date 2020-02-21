import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

data class AddResult(val first: Int, val second: Int, val result: String)

fun Application.adder() {
    val count = mutableMapOf<Int, Int>()
    install(ContentNegotiation) {
        gson { }
    }
    routing {
        get("/") {
            call.respondText(hello())
        }
        get("/add/{first}/{second}") {
            try {
                val first = call.parameters["first"]!!.toInt()
                val second = call.parameters["second"]!!.toInt()
                val addResult = AddResult(first, second, (first + second).toString())
                call.respond(addResult)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
        get("/count/{first}") {
            val first = call.parameters["first"]!!.toInt()
            val firstCount = count.getOrDefault(first, 0) + 1
            count[first] = firstCount
            call.respondText(firstCount.toString())
        }
    }
}
fun main() {
    embeddedServer(Netty, port = 8080, module = Application::adder).start(wait = true)
}

fun hello(): String {
    return "Hello!"
}
